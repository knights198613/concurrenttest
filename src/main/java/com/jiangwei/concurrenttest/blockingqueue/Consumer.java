package com.jiangwei.concurrenttest.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by weijiang
 * Date: 2017/6/19
 * Desc: 消费者
 */
public class Consumer implements Runnable {

    private BlockingQueue blockingQueue;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        try {
            System.out.println(this.blockingQueue.take());
            System.out.println(this.blockingQueue.take());
            System.out.println(this.blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
