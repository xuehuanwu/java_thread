package com.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableDemo {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "AA").start();
        // 两个线程调用同一个futureTask，则只有一个线程进入计算，另外一个线程复用其计算结果。如果都要进入计算，则调用不同的futureTask即可
        new Thread(futureTask, "BB").start();

        int i = 100;

        // 添加自旋锁，直到Callable计算完毕
        while (!futureTask.isDone()) {

        }

        // 要求获得Callable线程的计算结果放在最后，如果没有计算完成就去强求，会导致阻塞
        int j = futureTask.get();
        System.out.println("Callable return value: " + (i + j));
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t come in Callable");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}