package com.imooc.synchronize;

/**
 * 同时访问同步方法和非同步方法
 *
 * @author 58212
 * @date 2019-10-05 23:57
 */
public class SynchronizeYesOrNo6 implements Runnable {

    static SynchronizeYesOrNo6 instance1 = new SynchronizeYesOrNo6();

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
        System.out.println(Thread.currentThread().getName() + ": 当前加锁线程开始运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": 当前加锁线程结束运行");
    }

    private void methodB() {
        System.out.println(Thread.currentThread().getName() + ": 当前无锁线程开始运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": 当前无锁线程结束运行");
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
