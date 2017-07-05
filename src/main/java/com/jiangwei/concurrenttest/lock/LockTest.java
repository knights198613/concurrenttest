package com.jiangwei.concurrenttest.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by weijiang
 * Date: 2017/7/5
 * Desc:
 */
public class LockTest {

    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        LockTest lockTest = new LockTest();
        lockTest.doPrint();
    }

    void doPrint() {
        Printable printable = new Printable("aaaaaaaaa");
        Thread t1 = new Thread(printable, "t1");
        Thread t2 = new Thread(printable, "t2");
        t1.start();
        t2.start();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class Printable implements Runnable {
        private String name;

        public Printable(String name) {
            this.name = name;
        }

        public void run() {
            printString();
        }
    }


    public void printString() {

            lock.lock();
            if("t1".equals(Thread.currentThread().getName())) {
                for(int i=1; i<100; i++) {
                    System.out.println(Thread.currentThread().getName()+"_"+i);
                }
            }else if("t2".equals(Thread.currentThread().getName())) {
                for(int i=100; i<200; i++) {
                    System.out.println(Thread.currentThread().getName()+"_"+i);
                }
            }
            //Thread.sleep(3000);
            //System.out.println("Hello world!------threadName:"+Thread.currentThread().getName());
            lock.unlock();
    }
}
