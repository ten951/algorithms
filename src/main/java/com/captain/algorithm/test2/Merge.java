package com.captain.algorithm.test2;

/**
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

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j].compareTo(aux[i]) < 0) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }

        }
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void main(String[] args) {
        Merge<String> stringSelection = new Merge<String>();
        String[] strings = {"M", "D", "B", "H", "A", "S", "N", "C"};
        stringSelection.sort(strings);
        stringSelection.show(strings);
    }
}
