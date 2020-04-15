package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示AtomicInteger的基本用法,对比非原子类的线程安全问题, 使用了原子类之后,不需要加锁，也可以保证线程安全
 *
 * @author Haoming Chen
 * Created on 2020/4/13
 */
public class AtomicIntegerDemo1 implements Runnable {

    public static void main(String[] args) {
        Thread threadA = new Thread(new AtomicIntegerDemo1());
        Thread threadB = new Thread(new AtomicIntegerDemo1());
        threadA.start();
        threadB.start();
        try {
            threadB.join();
            threadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomic);
        System.out.println(atomicInteger.get());
    }

    public static volatile Integer atomic = 0;

    public static final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementInteger();
            incrementAtmoicInteger();
        }
    }

    private static synchronized void incrementInteger() {
        atomic++;
    }

    private void incrementAtmoicInteger() {
//        atomicInteger.getAndIncrement();
        atomicInteger.getAndAdd(-1);
    }
}
