package com.captain.algorithm.test3;

/**
 * 堆有序: 当一个颗二叉树的每个节点都大于等于它的两个子节点时,它被称为堆有序
 * 二叉堆: 一组能够用堆有序的完全二叉树排序的元素,并在数组中按照层级存储(不适用数组的第一个位置).
 * 位置k的节点的父节点的位置为[k/2],而它的两个子节点分别为2k和2k+1.
 * 基于二叉堆实现的优先队列
 *
 * @author Darcy
 * Created By Darcy on 2018/6/13 上午10:46
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;


    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 将新元素加到数组末尾,增加堆的大小并让新元素上浮到合适的位置.
     *
     * @param v 插入的元素
     */
    public void insert(Key v) {
        if (N == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++N] = v;
        swim(N);
    }

    /**
     * 从数组顶端删去最大的元素并将数组的最后一个元素放到顶端.减小堆的大小并让这个元素下沉到合适的位置.
     *
     * @return 最大的元素
     */
    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        if (N > 0 && N == (pq.length - 1) / 4) {
            resize(pq.length / 2);
        }
        sink(1);
        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }


    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }


    /**
     * 下浮
     *
     * @param k 节点
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /**
     * 上浮
     *
     * @param k 节点位置
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>(5);
        maxPQ.insert(1);
        maxPQ.insert(2);
        maxPQ.insert(5);
        maxPQ.insert(5);
        maxPQ.insert(7);
        maxPQ.insert(5);
        maxPQ.insert(8);
        maxPQ.insert(5);
        maxPQ.insert(9);
        maxPQ.insert(5);
        maxPQ.insert(4);
        maxPQ.insert(5);
        Integer integer = maxPQ.delMax();
        System.out.println("integer = " + integer);
    }

}
