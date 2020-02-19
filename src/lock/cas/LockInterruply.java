package lock.cas;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Haoming Chen
 * Created on 2020/2/20
 */
public class LockInterruply {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("被中断-可中断锁");
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        thread0.start();
        Thread.sleep(500);
        thread0.interrupt();
    }

}
