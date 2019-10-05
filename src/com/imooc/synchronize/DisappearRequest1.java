package com.imooc.synchronize;

/**
 * @author 58212
 * @date 2019-09-23 2:16
 */
public class DisappearRequest1 implements Runnable {

    static DisappearRequest1 instance = new DisappearRequest1();

    static int i = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }

    @Override
    public synchronized void run() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }

}
