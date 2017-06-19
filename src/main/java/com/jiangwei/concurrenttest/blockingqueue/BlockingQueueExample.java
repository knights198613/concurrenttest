package com.jiangwei.concurrenttest.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by weijiang
 * Date: 2017/6/19
 * Desc: 阻塞队列示例1
 */
public class BlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(1024);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        new Thread(producer).start();
        new Thread(consumer).start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
