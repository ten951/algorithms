package com.captain.algorithm.test1;

import java.util.Iterator;

/**
 * 基于链表实现的栈
 *
 * @author Darcy
 * Created By Darcy on 2018/6/5 下午1:42
 */
public class Stack<Item> implements Iterable<Item> {
    /**
     * 链表的第一个元素
     */
    private Node first;
    /**
     * 链表的长度
     */
    private int N;

    private class Node {
        /**
         * 链表存储的数据(泛型)
         */
        Item item;
        /**
         * 指向下一个节点的指针
         */
        Node next;
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
        first.item = item;
        N++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    private class StackIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {

        }
    }
}
