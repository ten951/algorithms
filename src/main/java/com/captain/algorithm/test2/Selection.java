package com.captain.algorithm.test2;

/**
 * @author Darcy
 * Created By Darcy on 2018/6/6 下午5:27
 */
public class Selection<T> extends BaseSort<T> {
    @Override
    public void sort(Comparable<T>[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[i])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Selection<String> stringSelection = new Selection<String>();
        String[] strings = {"A", "D", "B"};
        stringSelection.sort(strings);
        stringSelection.show(strings);

    }
}
