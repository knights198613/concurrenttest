package com.jiangwei.concurrenttest.lock;

import sun.misc.Unsafe;
import sun.reflect.Reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by weijiang
 * Date: 2017/7/6
 * Desc:
 */
public class UnsafeTest {

    public static void main(String[] args) {
        //Unsafe 类不能直接在外部使用，设计到许多底层内存操作，
        // 通过反射获取此类的调用类判断调用类的classLoader不为空直接抛出unsafe异常
        //保证只能有虚拟机boot时来加载此类的一个单列（私有构造方法）
        //Unsafe unsafe = Unsafe.getUnsafe();
        Unsafe unsafe = null;
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] data = new byte[10];
        System.out.println(Arrays.toString(data));
        int offset = unsafe.arrayBaseOffset(data.getClass());
        System.out.println(offset);

        unsafe.putByte(data, offset, (byte) 1);
        unsafe.putByte(data, offset+5, (byte) 10);

        byte result = unsafe.getByte(data, offset+5);
        System.out.println(result);

        System.out.println(Arrays.toString(data));
    }
}
