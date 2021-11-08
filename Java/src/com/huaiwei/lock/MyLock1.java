package com.huaiwei.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock1 implements Runnable {
    private int num = 100;
    private final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "----" + "进入方法");
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + "----" + "获取到锁");
                System.out.println(Thread.currentThread().getName() + "----" + "执行");
                while (num >= 0) {
                    System.out.println(Thread.currentThread().getName() + "----" + num--);
                }
                System.out.println(Thread.currentThread().getName() + "----" + "准备睡眠，模拟执行时间过长");
                Thread.sleep(5000);
            } else {
                System.out.println(Thread.currentThread().getName() + "----" + "没有获取到锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "----" + "释放锁");
            lock.unlock();
        }
    }
}
