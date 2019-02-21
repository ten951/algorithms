package com.captain.concurrency.base;

/**
 * @author Darcy
 * Created By Darcy on 2018/7/12 下午5:01
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

    protected BoundedBuffer(int capacity) {
        super(capacity);
    }


    public synchronized void put(V v) throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        doPut(v);
        notifyAll();
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }
}
