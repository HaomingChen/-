package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep不释放lock锁
 *
 * @author Haoming Chen
 * Created on 2019/10/21
 */
public class SleepDontReleaseLock {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(new TestSleepDontReleaseLock(lock)).start();
        new Thread(new TestSleepDontReleaseLock(lock)).start();
    }

}

class TestSleepDontReleaseLock implements Runnable {

    Lock lock;

    TestSleepDontReleaseLock(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + ": 线程获得了锁");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": 线程释放了锁");
        lock.unlock();
    }
}



