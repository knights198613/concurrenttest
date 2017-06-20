package com.jiangwei.concurrenttest.priorityblockingqueue;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by weijiang
 * Date: 2017/6/20
 * Desc: 阻塞队列容纳的对象
 */
public class PriorityElement<E extends Comparable<? super E>> implements Comparable<PriorityElement<E>> {

    private static final AtomicLong SEQUENCE = new AtomicLong(0);
    private final long sequenceNum;
    private E data;

    public PriorityElement(long sequenceNum, E data) {
        this.sequenceNum = SEQUENCE.getAndIncrement();
        this.data = data;
    }

    public int compareTo(PriorityElement<E> o) {
        int res = data.compareTo(o.getData());
        if(res == 0 && data != o.getData()) {
            res = (sequenceNum < o.getSequenceNum() ? -1 : 1);
        }
        return res;
    }

    public long getSequenceNum() {
        return sequenceNum;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
