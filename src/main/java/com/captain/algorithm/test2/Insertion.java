package com.captain.algorithm.test2;

/**
 * @author Darcy
 * Created By Darcy on 2018/6/7 下午6:55
 */
public class Insertion<T> extends BaseSort<T> {
    @Override
    public void sort(Comparable<T>[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            Comparable<T> e = arr[i];
            int j = i;
            for (; j > 0 && less(e, arr[j - 1]); j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void main(String[] args) {
        Insertion<String> stringSelection = new Insertion<String>();
        String[] strings = {"M", "D", "B", "H", "A"};
        stringSelection.sort(strings);
        stringSelection.show(strings);

    }
}
