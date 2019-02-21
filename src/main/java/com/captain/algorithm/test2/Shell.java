package com.captain.algorithm.test2;

/**
 * @author Darcy
 * Created By Darcy on 2018/6/8 下午4:36
 */
public class Shell<T> extends BaseSort<T> {


    @Override
    public void sort(Comparable<T>[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
