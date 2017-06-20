package com.jiangwei.concurrenttest.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by weijiang
 * Date: 2017/6/20
 * Desc: 优先级阻塞队列 示例
 */
public class PriorityBlockingQueueExample {

    public static void main(String[] args) {
        PriorityBlockingQueue<String> stringPriorityBlockingQueue = new PriorityBlockingQueue<String>(1024);
        stringPriorityBlockingQueue.put("value");
        stringPriorityBlockingQueue.put("val");
        stringPriorityBlockingQueue.put("values");
        stringPriorityBlockingQueue.put("va");
        try {
            System.out.println(stringPriorityBlockingQueue.take());
            System.out.println(stringPriorityBlockingQueue.take());
            System.out.println(stringPriorityBlockingQueue.take());
            System.out.println(stringPriorityBlockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
