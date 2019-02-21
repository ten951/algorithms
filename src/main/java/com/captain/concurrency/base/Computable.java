package com.captain.concurrency.base;

/**
 * @author Darcy
 * Created By Darcy on 2018/7/10 上午10:21
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
