package com.imooc.synchronize;

/**
 * 同时访问静态synchronize和非静态synchronize方法
 *
 * @author 58212
 * @date 2019-10-06 0:11
 */
public class SynchronizeStaticAndNormal8 implements Runnable {

    static SynchronizeStaticAndNormal8 synchronizedObjectMethod3 = new SynchronizeStaticAndNormal8();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            methodA();
        } else {
            methodB();
        }
    }

    //方法锁默认锁对象为this
    private synchronized void methodA() {
        System.out.println(Thread.currentThread().getName() + ": methodA, 线程运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": methodA, 线程运行结束");
    }

    //方法锁默认锁对象为Class对象
    private static synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + ": methodB, 线程运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": methodB, 线程运行结束");
    }

    public static void main(String[] args) {
        //两个实例同时访问一个对象的同步方法
        Thread thread1 = new Thread(synchronizedObjectMethod3);
        Thread thread2 = new Thread(synchronizedObjectMethod3);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {

        }
        System.out.println("结束");
    }

}
