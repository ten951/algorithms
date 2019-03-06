package com.captain.algorithm.test2;

/**
 * 数组中每个元素距离他最终位置都不远
 * 一个有序大数组接一个小数组
 * 数组中只有几个元素的位置不确定
 *
 * @author Darcy
 * Created By Darcy on 2018/6/7 下午6:55
 */
public class Insertion<T> extends BaseSort<T> {
    @Override
    public void sort(Comparable<T>[] arr) {
        final int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(arr[j], arr[j - 1]); j--) {
                exch(arr, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Insertion<String> stringSelection = new Insertion<String>();
        String[] strings = {"M", "D", "B", "H", "A"};
        stringSelection.sort(strings);
        stringSelection.show(strings);

    }
}
