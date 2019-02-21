package com.captain.algorithm.adtstackqueue;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Darcy
 * Created By Darcy on 2018/7/30 上午10:45
 */
public class Test {

    /**
     * 例如n=6 {1,2,3,4,5,6}
     *
     * @param n 初始化n的有序集合
     * @return 有序集合
     */
    private static int[] initA(int n) {
        int[] init = new int[n];
        for (int i = 0; i < n; i++) {
            init[i] = i + 1;
        }
        return init;
    }

    /**
     * 例如n=6 {1,2,3,4,5,6}
     *
     * @param n 目标集合个数
     * @return 原始集合 {1,4,2,6,3,5}
     * @throws InterruptedException 异常
     */
    public static int[] test(int n) throws InterruptedException {
        List<Integer> a = new ArrayList<>();
        int[] init = initA(n);
        BlockingQueue<Integer> uu = new ArrayBlockingQueue<>(n);
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                a.add(i);
            } else {
                uu.add(i);
            }
        }
        while (!uu.isEmpty()) {
            if (uu.size() == 1) {
                a.add(uu.take());
            } else {
                if (init.length % 2 == 0) {
                    a.add(uu.take());
                    uu.add(uu.take());
                } else {
                    uu.add(uu.take());
                    a.add(uu.take());
                }
            }
        }
        return print(a, init);
    }

    /**
     * 例如n=6 a={0,2,4,1,5,3} init={1,2,3,4,5,6}
     *
     * @param a    原始集合的index序列
     * @param init 有序集合
     * @return 原始集合
     */
    private static int[] print(List<Integer> a, int[] init) {
        int[] f = new int[a.size()];
        for (int i = 0; i < a.size(); i++) {
            f[a.get(i)] = init[i];
        }
        return f;
    }


    public static void main(String[] args) throws InterruptedException {
        int[] print = Test.test(6);
        for (int a : print) {
            System.out.print(a + " ");
        }
    }


}
