package com.jiangwei.concurrenttest.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by weijiang
 * Date: 2017/6/27
 * Desc: 并发计数器测试客户端
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Waiter waiter = new Waiter(countDownLatch);
        Determend determend = new Determend(countDownLatch);

        new Thread(waiter).start();
        new Thread(determend).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
