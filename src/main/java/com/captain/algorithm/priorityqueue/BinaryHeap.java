package com.captain.algorithm.priorityqueue;

import java.nio.BufferOverflowException;

/**
 * 最小二叉堆,更节点是最小元素,从1开始.0位置不用,任意节点就应该小于它的所有后裔
 * 堆是一颗被完全填满的二叉树,有可能的例外是底层,底层上的元素从左到右填入.这要的树叫完成二叉树
 * 因为完成二叉树这么规律,所以可以用数组表示.对于数组中任意位置i上的元素,其左儿子在位置2i上,有儿子在左儿子后的单元2i+1中,它的父亲则在位置i/2上.
 *
 * @author Darcy
 * Created By Darcy on 2018/6/25 上午11:01
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
    public static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private AnyType[] array;

    /**
     * 构建二叉堆
     *
     * @param capacity 二叉堆大小.
     */
    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (AnyType[]) new Comparable[capacity + 1];
    }

    /**
     * 通过数组构建二叉堆
     */
    public BinaryHeap(AnyType[] items) {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for (AnyType item : items) {
            array[i++] = item;
        }
        buildHeap();
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * 优先队列是否为null
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * 设置有限队列为null
     */
    public void makeEmpty() {
        currentSize = 0;
    }

    /**
     * 查询优先队列最小元素
     *
     * @return 值
     */
    public AnyType findMin() {
        if (isEmpty()) {
            throw new BufferOverflowException();
        }
        return array[1];
    }

    /**
     * 删除最小元素
     *
     * @return 最小元素的值
     */
    public AnyType deleteMin() {
        if (isEmpty()) {
            throw new BufferOverflowException();
        }

        AnyType minItem = findMin();
        /*将堆最后一元素插入根节点.并元素个数-1*/
        array[1] = array[currentSize--];
        /*下滤*/
        percolateDown(1);

        return minItem;
    }

    /**
     * 当我们删除最小元素后,堆就不是有序状态,恢复堆有序,也可以成为空穴下滤的过程
     * 下滤
     *
     * @param hole 节点
     */
    private void percolateDown(int hole) {
        int child;
        AnyType tmp = array[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            /*找到最小的儿子节点的值*/
            if (child != currentSize &&
                    array[child + 1].compareTo(array[child]) < 0) {
                child++;
            }
            /*该值赋值给父亲节点*/
            if (array[child].compareTo(tmp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tmp;
    }

    /**
     * 插入优先队列数据
     * <p>
     * 为将一个元素x插入堆中,在下一个可用位置创建一个空穴,否则该堆将不是完成树,如果x放在该空穴中而并不破坏堆的序,那么插入完成,
     * 否则 我们把空穴的父节点上的元素移入该空穴,这样空穴就朝着根方向冒一步,继续该过程直到X能被放入空穴为止.
     *
     * @param x 目标数据
     */
    public void insert(AnyType x) {
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }
        /*上浮*/
        int hole = ++currentSize;
        for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }


    private void enlargeArray(int newSize) {
        AnyType[] old = array;
        array = (AnyType[]) new Comparable[newSize];
        for (int i = 0; i < old.length; i++) {
            array[i] = old[i];
        }
    }
}
