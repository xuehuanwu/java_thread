package com.java.thread.operation;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);  // 模拟3个停车位

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(); // 占用(加)
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // 释放(减)
                }
            }, String.valueOf(i)).start();
        }
    }
}
