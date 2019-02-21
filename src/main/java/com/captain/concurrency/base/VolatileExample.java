package com.captain.concurrency.base;

/**
 * @author Darcy
 * Created By Darcy on 2018/8/31 下午2:19
 */
public class VolatileExample {
    volatile long vl = 1L; // 使用volatile声明64位的long型变量

    public void set(long l) {
        vl = l; //单个volatile变量的写
    }

    public long get() {
        return vl; //单个volatile变量的读
    }

    public void getAndIncrement() {
        vl++; // 复合(多个)volatile变量的读/写
    }
}
