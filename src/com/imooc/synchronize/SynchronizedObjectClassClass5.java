package com.imooc.synchronize;

/**
 * 类锁之class代码块锁
 *
 * @author 58212
 * @date 2019-10-05 23:21
 */
public class SynchronizedObjectClassClass5 implements Runnable {

    static SynchronizedObjectClassClass5 instance1 = new SynchronizedObjectClassClass5();
    static SynchronizedObjectClassClass5 instance2 = new SynchronizedObjectClassClass5();

    @Override
    public void run() {
        method();
    }

    private void method() {
        synchronized (SynchronizedObjectClassClass5.class) {
            System.out.println(Thread.currentThread().getName() + ": 当前线程开始运行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 当前线程结束运行");
        }
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(instance1);
        Thread threadB = new Thread(instance2);
        threadA.start();
        threadB.start();
        while(threadA.isAlive() || threadB.isAlive()){
        }
        System.out.println("线程运行结束");
    }
}
