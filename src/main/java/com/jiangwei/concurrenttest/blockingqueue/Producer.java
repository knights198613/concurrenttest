package com.jiangwei.concurrenttest.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by weijiang
 * Date: 2017/6/19
 * Desc: 生产者
 */
public class Producer implements Runnable{

    private BlockingQueue blockingQueue;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        try {
            this.blockingQueue.put(1111);
            Thread.sleep(1000);
            this.blockingQueue.put(2222);
            Thread.sleep(1000);
            this.blockingQueue.put(3333);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
