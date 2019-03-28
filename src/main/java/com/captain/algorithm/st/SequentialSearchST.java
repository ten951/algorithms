package com.captain.algorithm.st;

import com.captain.algorithm.test1.Queue;

/**
 * 基于无序链表(顺序查找)
 * 基于链表的实现以及顺序查找是非常低效的
 *
 * @author Darcy
 * Created By Darcy on 2019-03-12 15:32
 */
public class SequentialSearchST<K, V> {
    public SequentialSearchST() {
    }

    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node first;

    private int N;

    /**
     * 通过key查询value. 没有返回null
     *
     * @param key key
     * @return value
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }


    /**
     * 关联key-value
     * key已经存在替换value 不存在插入表头
     *
     * @param key   key
     * @param value value
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (value == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        N++;
    }

    public int size() {
        return N;
    }

    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        first = delete(first, key);
    }

    /**
     * 递归确定x是否是要删除的节点
     * true: first = x.next
     * false: 继续递归下一个节点(x.next)
     *
     * @param x   当前节点
     * @param key key
     * @return 链表头
     */
    private Node delete(Node x, K key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            N--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<K> keys() {
        Queue<K> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }
}
