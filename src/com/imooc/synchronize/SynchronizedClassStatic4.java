package com.imooc.synchronize;

/**
 * 类锁的第一种形式
 *
 * @author 58212
 * @date 2019-10-05 22:54
 */
public class SynchronizedClassStatic4 implements Runnable {
    static SynchronizedClassStatic4 instance1 = new SynchronizedClassStatic4();
    static SynchronizedClassStatic4 instance2 = new SynchronizedClassStatic4();

    @Override
    public void run() {
        method();
    }

    private static synchronized void method(){
        System.out.println(Thread.currentThread().getName() + ": 线程正在运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": 线程运行结束");
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {

        }
        System.out.println("结束");
    }
}
