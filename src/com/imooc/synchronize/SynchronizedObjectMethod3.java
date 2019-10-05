package com.imooc.synchronize;

/**
 * 对象锁实例2, 方法锁形式
 *
 * @author 58212
 * @date 2019-10-05 21:16
 */
public class SynchronizedObjectMethod3 implements Runnable {

    static SynchronizedObjectMethod3 synchronizedObjectMethod3 = new SynchronizedObjectMethod3();

    @Override
    public void run() {
        method();
    }

    //方法锁默认锁对象为this
    private synchronized void method() {
        System.out.println(Thread.currentThread().getName() + ": 线程运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": 线程运行结束");
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
