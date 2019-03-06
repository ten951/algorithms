package com.captain.algorithm.test2;

/**
 * 升级版插入排序(h有序数组)
 * <p>
 * 中等大小的数组它的运行时间是可以接受的
 * 代码量小,且简单,
 * 而且不需要额外的内存空间
 *
 * @author Darcy
 * Created By Darcy on 2018/6/8 下午4:36
 */
public class Shell<T> extends BaseSort<T> {


    @Override
    public void sort(Comparable<T>[] a) {
        final int n = a.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
                h = h / 3;
            }
        }

    }
}
