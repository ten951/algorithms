package com.captain.algorithm.test2;

/**
 * 保证任意长度N的数组排序所需时间和NLogN成正比, 但是它需要额外的空间和N成正比
 *
 * @author Darcy
 * Created By Darcy on 2018/6/11 上午11:47
 */
public class Merge<T> extends BaseSort<T> {
    private static Comparable[] aux;

    @Override
    public void sort(Comparable<T>[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);

    }

    private void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        /*将a数组 复制到aux数组中.*/
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            /*左半边用尽, 取右半边*/
            if (i > mid) {
                a[k] = aux[j++];
                /*右半边用尽, 取左半边*/
            } else if (j > hi) {
                a[k] = aux[i++];
                /*右半边的当前元素小于左半边的当前元素 取右半边的元素*/
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                /*右半边的当前元素大于等于左半边的当前元素, 取左半边的元素*/
            } else {
                a[k] = aux[i++];
            }

        }
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (lo <= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        /*将左半边排序*/
        sort(a, lo, mid);
        /*将右半边排序*/
        sort(a, mid + 1, hi);
        /*归并结果*/
        merge(a, lo, mid, hi);
    }

    public static void main(String[] args) {
        Merge<String> stringSelection = new Merge<>();
        String[] strings = {"M", "D", "B", "H", "A", "S", "N", "C"};
        stringSelection.sort(strings);
        stringSelection.show(strings);
    }
}
