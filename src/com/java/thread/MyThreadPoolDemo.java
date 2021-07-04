package com.java.thread;

import java.util.concurrent.*;

/**
 * 线程池
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        // Executors禁止使用，因为其参数workQueue或maximumPoolSize默认值约21亿，会造成OOM
        fixedThreadPool();
        singleThreadPool();
        cachedThreadPool();

        // 拒绝策略
        abortPolicy();
        callerRunsPolicy();
        discardOldestPolicy();
        discardPolicy();

        // CUP核数
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    /**
     * 创建固定线程数量的线程池
     * 适用于：执行长期的任务，性能好很多。
     * 底层：ThreadPoolExecutor + LinkedBlockingQueue
     */
    public static void fixedThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try {
            // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * 创建单线程化的线程池
     * 适用于：一个任务一个任务执行的场景
     * 底层：ThreadPoolExecutor + LinkedBlockingQueue
     */
    public static void singleThreadPool() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        try {
            // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * 创建可缓存扩容线程的线程池
     * 适用于：执行很多短期异步的小程序或者负载较轻的服务器
     * 底层：ThreadPoolExecutor + SynchronousQueue
     */
    public static void cachedThreadPool() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * 默认，直接抛出RejectedExecutionException异常，阻止系统正常运行
     */
    public static void abortPolicy() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                    try {
                        TimeUnit.SECONDS.sleep(6);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * "调用者运行"一种调节机制，该策略即不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
     */
    public static void callerRunsPolicy() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * 抛弃队列中等待最久的任务，然后把当前任务加入队列中，尝试再次提交当前任务
     */
    public static void discardOldestPolicy() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * 直接丢弃任务，不予任务处理也不抛出异常。如果允许任务丢失，这是最好的一种方案
     */
    public static void discardPolicy() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
