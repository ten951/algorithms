package com.captain.algorithm.test1;

import java.util.Iterator;

/**
 * 链表结构队列实现
 *
 * @author Darcy
 * Created By Darcy on 2018/6/6 下午4:32
 */
public class Queue<Item> implements Iterable<Item> {
    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class Node {

        /**
         * 数据
         */
        Item item;

        /**
         * 下一个节点
         */
        Node next;
    }

    /**
     * 队列头
     */
    private Node first;

    /**
     * 队列尾
     */
    private Node last;

    /**
     * 队列长度
     */
    private int N;

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    /**
     * 入队
     *
     * @param item 入队列数据
     */
    public void enqueue(Item item) {
        /*从表尾添加数据*/
        /*老队尾 node*/
        Node oldLast = last;
        /*创建新的队尾node*/
        last = new Node();
        /*赋值为item*/
        last.item = item;
        /*队尾的下一个节点为null*/
        last.next = null;
        /*队列为null时, 队列头和尾是一个node*/
        if (isEmpty()) {
            first = last;
        } else {
            /*老队尾的下一个node是新队尾*/
            oldLast.next = last;
        }
        /*队列长度+1*/
        N++;
    }

    /**
     * 出队
     * @return 出队
     */
    public Item dequeue() {
        /*从表头删除元素, 拿到老表头的数据item*/
        Item item = first.item;
        /*新表头是老表头的下一个node*/
        first = first.next;
        /*如果出队后为null了. last = null*/
        if (isEmpty()) {
            last = null;
        }
        /*队列长度-1*/
        N--;
        return item;
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }
}
