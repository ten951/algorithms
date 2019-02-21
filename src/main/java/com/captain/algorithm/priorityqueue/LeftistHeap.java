package com.captain.algorithm.priorityqueue;

import com.captain.algorithm.UnderflowException;

/**
 * @author Darcy
 * Created By Darcy on 2018/7/20 下午6:29
 */
public class LeftistHeap<AnyType extends Comparable<? super AnyType>> {
    /**
     * Construct the leftist heap.
     */
    public LeftistHeap() {
        root = null;
    }

    /**
     * 合并rhs到优先队列内
     *
     * @param rhs 另外的左式堆
     */
    public void merge(LeftistHeap<AnyType> rhs) {
        /*自己不能合并自己*/
        if (this == rhs) {
            return;
        }

        root = merge(root, rhs.root);
        rhs.root = null;
    }

    /**
     * 内部方法合并两个根
     * 处理异常情况,并且调用merge1
     */
    private LeftistNode<AnyType> merge(LeftistNode<AnyType> h1, LeftistNode<AnyType> h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        /*将较大根的堆和较小根的右子树合并*/
        if (h1.element.compareTo(h2.element) < 0) {
            return merge1(h1, h2);
        } else {
            return merge1(h2, h1);
        }
    }

    /**
     * 合并两个堆的内部方法
     * h1是含有较小根的堆
     */
    private LeftistNode<AnyType> merge1(LeftistNode<AnyType> h1, LeftistNode<AnyType> h2) {
        /*较小的h1 是单个节点*/
        if (h1.left == null) {
            h1.left = h2;
        } else {
            /*将较大根h2合并到较小根的h1的右子树上*/
            h1.right = merge(h1.right, h2);
            /*如果h1左子树的npl小于右子树的npl*/
            if (h1.left.npl < h1.right.npl) {
                swapChildren(h1);
            }
            /*任意一个节点npl都等于它右儿子节点的npl+1*/
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    /**
     * 交换左右子树
     */
    private static <AnyType> void swapChildren(LeftistNode<AnyType> t) {
        LeftistNode<AnyType> tmp = t.left;
        t.left = t.right;
        t.right = tmp;
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        root = merge(new LeftistNode<>(x), root);
    }

    /**
     * Find the smallest item in the priority queue.
     *
     * @return the smallest item, or throw UnderflowException if empty.
     */
    public AnyType findMin() {
        if (isEmpty())
            throw new UnderflowException();
        return root.element;
    }

    /**
     * Remove the smallest item from the priority queue.
     *
     * @return the smallest item, or throw UnderflowException if empty.
     */
    public AnyType deleteMin() {
        if (isEmpty())
            throw new UnderflowException();

        AnyType minItem = root.element;
        root = merge(root.left, root.right);

        return minItem;
    }

    /**
     * Test if the priority queue is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    private static class LeftistNode<AnyType> {
        LeftistNode(AnyType theElement) {
            this(theElement, null, null);
        }

        LeftistNode(AnyType theElement, LeftistNode<AnyType> lt, LeftistNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
            npl = 0;
        }

        /**
         * 数据节点
         */
        AnyType element;
        /**
         * 左儿子
         */
        LeftistNode<AnyType> left;
        /**
         * 右儿子
         */
        LeftistNode<AnyType> right;
        /**
         * 零路径长
         */
        int npl;
    }

    private LeftistNode<AnyType> root;    // root

    public static void main(String[] args) {
        int numItems = 100;
        LeftistHeap<Integer> h = new LeftistHeap<>();
        LeftistHeap<Integer> h1 = new LeftistHeap<>();
        int i = 37;

        for (i = 37; i != 0; i = (i + 37) % numItems) {
            if (i % 2 == 0) {
                h1.insert(i);
            } /*else {
                h.insert(i);
            }*/
        }
        h.insert(1);
        h.merge(h1);
        for (i = 1; i < numItems; i++) {
            if (h.deleteMin() != i) {
                System.out.println("Oops! " + i);
            }
        }
    }
}
