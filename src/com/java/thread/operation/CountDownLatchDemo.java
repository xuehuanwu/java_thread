package com.java.thread.operation;

import com.java.thread.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，调用线程会被阻塞，
 * 其他线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因调用await方法被阻塞的线程会被唤醒，继续执行。
 * =====================================================================================================================
 * 做减法运算
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "国，被灭");
                countDownLatch.countDown();
            }, CountryEnum.getByCode(i).getDescribe()).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 秦国，一统天下");
    }

    public static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 上晚自习，离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 班长最后关门走人");
    }
}
