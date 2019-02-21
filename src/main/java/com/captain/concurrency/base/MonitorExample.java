package com.captain.concurrency.base;

/**
 * @author Darcy
 * Created By Darcy on 2018/8/31 下午2:59
 */
public class MonitorExample {
    int a = 0;
    public synchronized  void writer() { //1
        a++;//2
    }//3

    public synchronized void reader() {//4
        int i =a;//5
    }//6
}
