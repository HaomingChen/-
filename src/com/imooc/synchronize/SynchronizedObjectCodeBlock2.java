package com.imooc.synchronize;

/**
 * 对象锁之同步代码块锁
 *
 * @author 58212
 * @date 2019-10-05 20:17
 */
public class SynchronizedObjectCodeBlock2 implements Runnable {

    static SynchronizedObjectCodeBlock2 synchronizedObjectCodeBlock2 = new SynchronizedObjectCodeBlock2();

    static Object lockA = new Object();
    static Object lockB = new Object();
    @Override
    public void run() {
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + ": 线程运行中");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 线程运行结束");
        }
//        synchronized (lockA) {
//            System.out.println(Thread.currentThread().getName() + ": 线程运行中");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        synchronized (lockB) {
//            System.out.println(Thread.currentThread().getName() + ": 线程运行中");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(synchronizedObjectCodeBlock2);
        Thread thread2 = new Thread(synchronizedObjectCodeBlock2);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {

        }
        System.out.println("结束");
    }
}
