package com.jiangwei.concurrenttest.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by weijiang
 * Date: 2017/6/19
 * Desc: 延迟元素
 */
public class DelayElement implements Delayed {
    private Integer num;

    public DelayElement(Integer num) {
        this.num = num;
    }

    public long getDelay(TimeUnit unit) {
        return 3;
    }

    public int compareTo(Delayed o) {
        if(this.num > ((DelayElement)o).num) {
            return 1;
        }else if (this.num == ((DelayElement)o).num) {
            return 0;
        }else{
            return -1;
        }
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
