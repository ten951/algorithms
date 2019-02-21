package com.captain.algorithm.test1;

import java.util.Iterator;

/**
 * 能够动态调整数组大小的下压栈
 *
 * @author Darcy
 * Created By Darcy on 2018/6/5 下午12:52
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
    /**
     * stack entries
     */
    private Item[] a = (Item[]) new Object[1];
    /**
     * size
     */
    private int N;


    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ResizingArrayIterator();
    }

    private class ResizingArrayIterator implements Iterator<Item> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {

        }
    }
}
