package com.jiangwei.concurrenttest.future;

import java.util.concurrent.*;

/**
 * Created by weijiang
 * Date: 2017/7/3
 * Desc:
 */
public class TestFutureTaskClient {

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<String>(new RealData("name"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(futureTask);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*try {
            System.out.println("结果："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        executorService.shutdownNow();*/
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor)executorService;
        int i = poolExecutor.getPoolSize();
        System.out.println("线程池持有线程数："+i);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }
}
