package threadcoreknowledge.background;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 必然死锁
 *
 * @author Haoming Chen
 * Created on 2019/10/24
 */
public class DeadLock {
    public static void main(String[] args) {
        DeadLock deadLockA = new DeadLock();
        DeadLock deadLockB = new DeadLock();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(() -> {
            synchronized (deadLockB) {
                System.out.println("get lockB");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                synchronized (deadLockA) {
                    System.out.println("进不来");
                }
            }
            System.out.println(Thread.currentThread().getName() + "线程运行完毕");
        }).start();

        new Thread(() -> {
            synchronized (deadLockA) {
                System.out.println("get lockA");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                synchronized (deadLockB) {

                }
            }
            System.out.println(Thread.currentThread().getName() + "线程运行完毕");
        }).start();
    }

}
