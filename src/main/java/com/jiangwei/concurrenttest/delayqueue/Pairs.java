package com.jiangwei.concurrenttest.delayqueue;

/**
 * Created by weijiang
 * Date: 2017/6/20
 * Desc: 缓存键值 对象类
 */
public class Pairs<K, V> {
    private K key;
    private V value;

    public Pairs(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
