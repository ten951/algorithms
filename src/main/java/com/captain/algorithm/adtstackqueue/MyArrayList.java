package com.captain.algorithm.adtstackqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 自己实现的ArrayList
 * 1 保持基础数组,数组的容量,已经存储在MyArrayList中的当前项数
 * 2 提供一种机制以改变基础数组的容量,通过获得一个新数组,将老数组拷贝到新数组中来改变数组的容量,允许虚拟机回收老数组
 * 3 提供get和set方法
 * 4 提供基本方法 如 size isEmpty clear;还提供remove,已经两种不同版本的add.如果数组的大小和容量相同,那么这两个add都将增加容量
 * 5 提供实现Iterator接口类,这个类提供存储迭代序列中的下一项的下标,并提供next hasNext remove等方法实现,
 *
 * @author Darcy
 * Created By Darcy on 2018/6/19 上午11:26
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;

    private AnyType[] theItems;

    public MyArrayList() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public void ensureCapacity(int newCapacity) {
        /*theSize 数组实际大小*/
        if (newCapacity < theSize) {
            return;
        }
        /*创建新的数组,然后复制老数组值入新数组*/
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public AnyType remove(int idx) {
        AnyType removeItem = theItems[idx];
        /*如果删除的元素是不是尾,那就需要高位向前移动*/
        for (int i = idx; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removeItem;
    }

    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x) {
        /*当存值得数组大小和list大小相等时,把存值数组扩容为1倍*/
        if (theItems.length == size()) {
            ensureCapacity(2 * theItems.length);
        }
        /*如果是在数组中间插入,后面的元素要向高位移动*/
        for (int i = theSize; i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = x;
        theSize++;

    }

    public AnyType get(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    public AnyType set(int idx, AnyType newVal) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<AnyType> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        myArrayList.add(8);
        myArrayList.add(9);
        myArrayList.add(6,10);
    }
}
