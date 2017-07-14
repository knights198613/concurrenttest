package com.jiangwei.jvmtest.oomtest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weijiang
 * Date: 2017/7/14
 * Desc: 测试jvm 堆内存溢出
 */
public class OOMTest {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> oomObjects = new ArrayList<OOMObject>();
        while(true) {
            oomObjects.add(new OOMObject());
        }
    }
}
