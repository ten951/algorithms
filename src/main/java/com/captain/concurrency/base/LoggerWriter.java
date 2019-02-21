package com.captain.concurrency.base;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * @author Darcy
 * Created By Darcy on 2018/7/16 上午10:33
 */
public class LoggerWriter<T> implements Callable<T> {
    private final BlockingQueue<T> queue;
    private final T json;

    public LoggerWriter(BlockingQueue<T> queue, T json) {
        this.queue = queue;
        this.json = json;
    }

    @Override
    public T call() throws Exception {
        writer(json);
        return null;
    }

    private void writer(T json) throws InterruptedException {
        System.out.println("json = " + json);
        queue.put(json);
    }
}
