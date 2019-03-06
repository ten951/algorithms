package com.captain.algorithm.test2;

import com.captain.algorithm.StdOut;
import com.captain.algorithm.StdRandom;

/**
 * @author Darcy
 * Created By Darcy on 2018/7/27 下午12:01
 */
public class Quick {
    private Quick() {
    }


    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        assert isSorted(a, lo, hi);
    }

    /**
     * 切分数组
     * 切分数组 a[lo..hi] 要达成 a[lo..j-1] <= a[j] <= a[j+1..hi]
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        /*扫描左右的指针*/
        int i = lo;
        int j = hi + 1;
        /*切分的元素*/
        Comparable v = a[lo];
        while (true) {

            /*找到切分元素v小的i索引*/
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            /*找到比切分元素v大的索引j*/
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            /*检测i和j 防止交叉*/
            if (i >= j) {
                break;
            }
            /*交换i,j的位置, 实现 小元素在v的左边, 大元素在v的右边 大小是相对v的*/
            exch(a, i, j);
        }

        /*最后交换切分元素v和j的位置*/
        exch(a, lo, j);

        /*a[lo .. j-1] <= a[j] <= a[j+1 .. hi] 完成*/
        return j;
    }


    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if (i > k) {
                hi = i - 1;
            } else if (i < k) {
                lo = i + 1;
            } else {
                return a[i];
            }
        }
        return a[lo];
    }


    private static boolean less(Comparable v, Comparable w) {
        if (v == w) {
            return false;
        }
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }


    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them;
     * and prints them to standard output in ascending order.
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = {"a", "c", "e", "t", "d", "g", "b", "r"};
        Quick.sort(a);
        show(a);
        assert isSorted(a);

        // shuffle
        StdRandom.shuffle(a);

        // display results again using select
        StdOut.println();
        for (int i = 0; i < a.length; i++) {
            String ith = (String) Quick.select(a, i);
            StdOut.println(ith);
        }
    }
}
