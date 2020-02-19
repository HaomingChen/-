package lock.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Haoming Chen
 * Created on 2020/2/20
 */
public class SpinLock {

    private AtomicReference<Thread> lock = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!lock.compareAndSet(null, current)) {
            System.out.println("CAS自旋尝试");
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        lock.compareAndSet(current, null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                spinLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    spinLock.unlock();
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
