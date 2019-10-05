package com.imooc.synchronize;

/**
 * 访问同一个对象的不同的普通同步方法访问同一个对象的不同的普通同步方法
 *
 * @author 58212
 * @date 2019-10-06 0:02
 */
public class SynchronizedDifferentMethod7 implements Runnable {

    static SynchronizedDifferentMethod7 instance1 = new SynchronizedDifferentMethod7();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            methodA();
        } else {
            methodB();
        }
    }

    //默认锁this
    private synchronized void methodA() {
        System.out.println(Thread.currentThread().getName() + ": 当前加锁线程开始运行A");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": 当前加锁线程结束运行A");
    }

    private synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + ": 当前加锁线程开始运行B");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": 当前加锁线程结束运行B");
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(instance1);
        Thread threadB = new Thread(instance1);
        threadA.start();
        threadB.start();
        while (threadA.isAlive() || threadB.isAlive()) {
        }
        System.out.println("线程运行结束");
    }

}
