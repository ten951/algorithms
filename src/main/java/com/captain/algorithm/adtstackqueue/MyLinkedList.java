package com.captain.algorithm.adtstackqueue;

/**
 * 手写linkList(双向链表实现)
 *
 * @author Darcy
 * Created By Darcy on 2018/6/19 下午3:35
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
    /**
     * 表的大小
     */
    private int theSize;
    /**
     * 从构造以来对链表所做改变的次数
     */
    private int modCount = 0;
    /**
     * 头节点
     */
    private Node<AnyType> beginMarker;
    /**
     * 尾节点
     */
    private Node<AnyType> endMarker;

    /**
     * 链表节点
     *
     * @param <AnyType>
     */
    private static class Node<AnyType> {
        /**
         * @param d 数据
         * @param p 上一个节点
         * @param n 下一个节点
         */
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }

        /**
         * 数据
         */
        public AnyType data;
        /**
         * 上一个节点
         */
        public Node<AnyType> prev;
        /**
         * 下一个节点
         */
        public Node<AnyType> next;
    }

    public void clear() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;

    }

    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    /**
     * 获取idx位置节点
     *
     * @param idx 获取node的位置
     * @return 节点
     */
    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);

    }

    /**
     * 获取idx位置的node
     *
     * @param idx   索引
     * @param lower 开始位置
     * @param upper 结束位置
     * @return 节点
     */
    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p;
        if (idx < lower || idx > upper) {
            throw new IndexOutOfBoundsException();
        }
        /*判断索引idx的位置,小于size的一半,就从头开始遍历*/
        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
            /*大于size的一半.就从尾部向前遍历*/
        } else {
            p = endMarker;
            for (int i = size(); i < idx; i--) {
                p = p.prev;

            }
        }
        return p;
    }

    /**
     * 删除目标节点
     *
     * @param p 目标节点
     * @return 删除的数据
     */
    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }

    /**
     * 向目标元素p之前插入x
     *
     * @param p 目标p
     * @param x 插入的元素
     */
    private void addBefore(Node<AnyType> p, AnyType x) {
        /*构建一个新node,prev是p.prev,next是p  1,3*/
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        /*新node的上一个节点的next 指向自己 2*/
        newNode.prev.next = newNode;
        /*p的prev 指向自己 4*/
        p.prev = newNode;
        theSize++;
        modCount++;

    }

    /**
     * 清空链表
     * 设置头尾节点,并连接起来,设置链表大小为0,操作次数+1
     */
    private void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;
        theSize = 0;
        modCount++;
    }

    @Override
    public java.util.Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }


    private class LinkedListIterator implements java.util.Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }
}
