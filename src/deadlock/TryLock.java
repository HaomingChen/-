package deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用TryLock解决死锁问题
 *
 * @author Haoming Chen
 * Created on 2019/11/12
 */
public class TryLock implements Runnable {

    static Lock resourceA = new ReentrantLock();
    static Lock resourceB = new ReentrantLock();
    int flag = 1;

    public TryLock(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) {
                try {
                    if (resourceA.tryLock(500, TimeUnit.MILLISECONDS)) {
                        doAction("get lock A");
                        if (resourceB.tryLock(500, TimeUnit.MILLISECONDS)) {
                            doAction("get lock B");
                            doAction("release lock B");
                            resourceA.unlock();
                            resourceB.unlock();
                            break;
                        } else {
                            doAction("release lock A");
                            resourceA.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (flag == 0) {
                try {
                    if (resourceB.tryLock(3000, TimeUnit.MILLISECONDS)) {
                        doAction("get lock B");
                        if (resourceA.tryLock(3000, TimeUnit.MILLISECONDS)) {
                            doAction("get lock A");
                            doAction("release lock A");
                            resourceA.unlock();
                            resourceB.unlock();
                            break;
                        } else {
                            doAction("release lock B");
                            resourceB.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void doAction(String action) throws InterruptedException {
        StringBuilder sb = new StringBuilder(Thread.currentThread().getName());
        sb.append(" : ");
        System.out.println(sb.append(action));
        Thread.sleep((long) (Math.random() * 10));
    }

    public static void main(String[] args) {
        new Thread(new TryLock(0)).start();
        new Thread(new TryLock(1)).start();
    }
}
