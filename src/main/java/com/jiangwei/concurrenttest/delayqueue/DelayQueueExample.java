package com.jiangwei.concurrenttest.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * Created by weijiang
 * Date: 2017/6/19
 * Desc: 延迟队列 示例1
 */
public class DelayQueueExample {

    public static void main(String[] args) {
        DelayQueue<DelayElement> delayElementDelayQueue = new DelayQueue<DelayElement>();

        DelayElement delayElement10 = new DelayElement(10);
        DelayElement delayElement100 = new DelayElement(100);

        delayElementDelayQueue.put(delayElement10);
        delayElementDelayQueue.put(delayElement100);


        try {
            System.out.println(delayElementDelayQueue.take().getNum());
            System.out.println(delayElementDelayQueue.take().getNum());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
