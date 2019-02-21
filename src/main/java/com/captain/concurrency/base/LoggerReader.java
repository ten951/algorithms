package com.captain.concurrency.base;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author Darcy
 * Created By Darcy on 2018/7/16 上午10:39
 */
public class LoggerReader<T> implements Callable<T> {
    private final BlockingQueue<T> queue;

    public LoggerReader(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public T call() {
        while (true) {
            try {
                return queue.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    private static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(200);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 100, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(300), namedThreadFactory);

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.submit(new LoggerWriter<>(queue, "s" + i));
        }
        for (int i = 0; i < 100; i++) {
            Future<String> submit = threadPoolExecutor.submit(new LoggerReader<>(queue));
            try {
                String s = submit.get(1, TimeUnit.SECONDS);
                System.out.println("s = " + s);
            } catch (InterruptedException | TimeoutException e) {
                submit.cancel(true);
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
        }
    }
}
