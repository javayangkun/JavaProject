package com.huaiwei.lock;

public class TestMyLock {
    public static void main(String[] args) {
        Runnable r = new MyLock1();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        Thread t4 = new Thread(r);
        Thread t5 = new Thread(r);
        Thread t7 = new Thread(r);
        Thread t6 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
    }
}
