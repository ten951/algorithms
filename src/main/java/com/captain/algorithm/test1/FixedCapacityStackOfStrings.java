package com.captain.algorithm.test1;

/**
 * 定容栈
 *
 * @author Darcy
 * Created By Darcy on 2018/6/5 上午11:58
 */
public class FixedCapacityStackOfStrings {

    /**
     * stack entries
     */
    private String[] a;
    /**
     * size
     */
    private int N;

    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        a[N++] = item;
    }

    public String pop() {
        return a[--N];
    }
}
