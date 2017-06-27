package com.jiangwei.concurrenttest.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by weijiang
 * Date: 2017/6/27
 * Desc: 计数执行线程
 */
public class Determend implements Runnable {

    private CountDownLatch countDownLatch;

    public Determend(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Determend count 1");
            this.countDownLatch.countDown();
            Thread.sleep(1000);
            System.out.println("Determend count 2");
            this.countDownLatch.countDown();
            Thread.sleep(1000);
            System.out.println("Determend count 3");
            this.countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
