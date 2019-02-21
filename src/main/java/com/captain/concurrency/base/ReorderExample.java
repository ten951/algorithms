package com.captain.concurrency.base;

/**
 * @author Darcy
 * Created By Darcy on 2018/8/31 下午1:48
 */
class ReorderExample {
    int a = 0;
    boolean flag = false;

    public void writer() {
        a = 1; //1
        flag = true; //2
    }

    public void reader() {
        if (flag) { //3
            int i = a * a; //4

        }
    }
}
