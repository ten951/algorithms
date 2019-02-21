package com.captain.concurrency.base;

/**
 * @author Darcy
 * Created By Darcy on 2018/8/31 下午2:18
 */
public class VolatileFeaturesExample {
    long vl = 0L;

    public synchronized void set(long l) { //对单个的普通变量的写用同一个锁同步
        vl = l;
    }

    public synchronized long get() { //对单个的普通变量的读用同一个锁同步
        return vl;
    }

    public void getAndIncrement() { //普通方法调通
        long temp = get(); //调用已同步的读方法
        temp += 1L;// 普通写操作
        set(temp); // 调用已同步的写方法
    }
}
