package com.jiangwei.concurrenttest.delayqueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by weijiang
 * Date: 2017/6/20
 * Desc: 缓存类
 */
public class Cache<K, V> {
    private static final Logger LOGGER = Logger.getLogger(Cache.class.getName());

    private ConcurrentMap<K, V> concurrentMap = new ConcurrentHashMap<K, V>();

    private DelayQueue<DelayedItem<Pairs<K, V>>> delayedItemDelayQueue = new DelayQueue<DelayedItem<Pairs<K, V>>>();

    private Thread deamThread;


    public Cache() {
        Runnable deamon = new Runnable() {
            public void run() {
                deamonCheck();
            }
        };
        deamThread = new Thread(deamon);
        deamThread.setDaemon(true);
        deamThread.setName("deamThreadCheck");
        deamThread.start();
    }

    /**
     * 后台守护线程的检查逐出方法
     */
    private void deamonCheck() {
        if (LOGGER.isLoggable(Level.INFO))
            LOGGER.info("cache service started!");
        for ( ; ; ) {
            try {
                DelayedItem<Pairs<K, V>> delayedItem = delayedItemDelayQueue.take();
                if (delayedItem != null) {
                    Pairs<K, V> pairs = delayedItem.getItem();
                    concurrentMap.remove(pairs.getKey(), pairs.getValue());
                }
            } catch (InterruptedException e) {
                if (LOGGER.isLoggable(Level.SEVERE))
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                break;
            }
        }

        if (LOGGER.isLoggable(Level.INFO))
            LOGGER.info("cache service stoped!");
    }

    /**
     * 添加缓存对象
     *
     * @param key
     * @param value
     * @param timeOut  失效时间
     * @param timeUnit 时间单位
     */
    public void put(K key, V value, long timeOut, TimeUnit timeUnit) {
        V oldValue = concurrentMap.put(key, value);
        if (oldValue != null) {
            //TODO 这里起始真的删除不了，这地方的移除方法暂时无解
            boolean fg = delayedItemDelayQueue.remove(key);
            System.out.println(fg);
        }
        long nanoTime = TimeUnit.NANOSECONDS.convert(timeOut, timeUnit);
        delayedItemDelayQueue.put(new DelayedItem<Pairs<K, V>>(new Pairs<K, V>(key, value), nanoTime));
    }

    /**
     * 获取缓存对象
     *
     * @param key
     * @return
     */
    public V getValue(K key) {
        return concurrentMap.get(key);
    }


    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {
        Cache<Integer, String> myCache = new Cache<Integer, String>();
        myCache.put(1, "aaaa", 5, TimeUnit.SECONDS);
        myCache.put(1, "bbbbbb", 5, TimeUnit.SECONDS);
        try {
            Thread.sleep(1000*3);
            String value = myCache.getValue(1);
            System.out.println(value);
            Thread.sleep(1000*3);
            String values = myCache.getValue(1);
            System.out.println(values);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
