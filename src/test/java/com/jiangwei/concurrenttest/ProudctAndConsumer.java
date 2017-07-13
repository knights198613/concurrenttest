package com.jiangwei.concurrenttest;

/**
 * Created by weijiang
 * Date: 2017/7/13
 * Desc: 生产者和消费者测试
 */
public class ProudctAndConsumer {


    public static void main(String[] args) {
          ProudctAndConsumer pc = new ProudctAndConsumer();
          pc.doTest();

    }

    void doTest() {
       final ProductConsumer productConsumer = new ProductConsumer(false, 100, true);
       new Thread(new Runnable() {
           public void run() {
               productConsumer.product();
           }
       }).start();

       new Thread(new Runnable() {
           public void run() {
               productConsumer.consumer();
           }
       }).start();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {

        }
        productConsumer.shutDown();
    }

    class ProductConsumer{
        final Object lock = new Object();
        boolean created = false;
        int i=0;
        boolean flag = false;

        public ProductConsumer(boolean created, int i, boolean flag) {
            this.created = created;
            this.i = i;
            this.flag = flag;
        }

        void product() {
            while (flag) {
                synchronized (lock) {
                    if (!created) {
                        i++;
                        System.out.println("product Method:" + i);
                        created = true;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        void consumer() {
            while (flag) {
                synchronized (lock) {
                    if (created) {
                        System.out.println("consumer Method:" + i);
                        created = false;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        void shutDown() {
            flag = false;
        }
    }


}
