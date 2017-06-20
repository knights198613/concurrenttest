package com.jiangwei.concurrenttest.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by weijiang
 * Date: 2017/6/20
 * Desc: 缓存条目
 */
public class DelayedItem<T> implements Delayed {
    /**
     * 期初时间取系统纳秒值
     */
    private static final long ORIGN_TIME = System.nanoTime();

    /**
     * 保证线程安全
     */
    private static final AtomicLong atomicLong = new AtomicLong(0);
    /**
     * 缓存条目
     */
    private T item;
    /**
     * 序列码
     */
    private Long sequecenNum;

    private final long time;

    public DelayedItem(T item, long timeout) {
        this.item = item;
        this.time = getNow() + timeout;
        this.sequecenNum = getSequecen();
    }

    /**
     * 序列码生产器
     *
     * @return
     */
    private Long getSequecen() {
        return atomicLong.getAndIncrement();
    }

    /**
     * 获取当前的系统纳秒值与起始记录点的间距
     *
     * @return
     */
    private long getNow() {
        return System.nanoTime() - ORIGN_TIME;
    }


    public long getDelay(TimeUnit unit) {
        long d = unit.convert(time - getNow(), TimeUnit.NANOSECONDS);
        return d;
    }

    public int compareTo(Delayed o) {
        if(this == o) {
            return 0;
        }
        if(o instanceof DelayedItem) {
            DelayedItem other = (DelayedItem)o;
            long diff = this.time - other.time;
            long diffSequenceNum = this.sequecenNum - other.sequecenNum;
            if(diff < 0) {
                return -1;
            }
            if(diff > 0) {
                return 1;
            }
            if(diffSequenceNum < 0) {
                return -1;
            }
            if(diffSequenceNum > 0) {
                return 1;
            }
        }
        long d = (getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS));
        return d == 0 ? 0 : ((d < 0) ? -1 : 1);
    }


    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Long getSequecenNum() {
        return sequecenNum;
    }

    public void setSequecenNum(Long sequecenNum) {
        this.sequecenNum = sequecenNum;
    }

    public long getTime() {
        return time;
    }
}
