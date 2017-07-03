package com.jiangwei.concurrenttest.future;

import java.util.concurrent.Callable;

/**
 * Created by weijiang
 * Date: 2017/7/3
 * Desc:
 */
public class RealData implements Callable<String> {

    private String data;

    public RealData(String data) {
        this.data = data;
    }

    public String call() throws Exception {
        Thread.sleep(5000);
        return data;
    }
}
