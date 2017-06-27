package com.jiangwei.concurrenttest.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by weijiang
 * Date: 2017/6/27
 * Desc: 等待执行线程
 */
public class Waiter implements Runnable {

    private CountDownLatch countDownLatch;

    public Waiter(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Waiter has Released");

    }
}
