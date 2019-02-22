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

    /**
     * 比较i索引元素是否小于j索引元素
     *
     * @param i 索引
     * @param j 素银
     * @return true pq[i] < pq[j]
     */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 交换索引i和j的元素值
     *
     * @param i 索引
     * @param j 索引
     */
    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }


    /**
     * 改变数据大小为capacity
     *
     * @param capacity 数据大小
     */
    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }


    /**
     * 自上而下循环比较
     * 下浮
     *
     * @param k 节点
     */
    private void sink(int k) {
        /*从k节点开始,一直循环到最后, 找k节点合适的位置*/
        while (2 * k <= N) {
            /*k的左儿子节点, j*/
            int j = 2 * k;
            /*j不是左后一个, 并且左儿子小于右儿子, j++*/
            if (j < N && less(j, j + 1)) {
                j++;
            }
            /*当k>=j大, 结束循环, 这就是他的正确位置*/
            if (!less(k, j)) {
                break;
            }
            /*k<j的时候, 交换 k和j的位置*/
            exch(k, j);
            /*k的值=j*/
            k = j;
        }
    }

    /**
     * 上浮
     *
     * @param k 节点位置
     */
    private void swim(int k) {
        /*k不在顶点 并且 k/2(父节点) 小于 k节点*/
        while (k > 1 && less(k / 2, k)) {
            /*交换父节点和k的位置*/
            exch(k / 2, k);
            /*k = 父节点的索引*/
            k = k / 2;
        }
    }


}
