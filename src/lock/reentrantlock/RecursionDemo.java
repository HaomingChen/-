package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Haoming Chen
 * Created on 2020/2/16
 */
public class RecursionDemo {

    static ReentrantLock lock = new ReentrantLock();

    private static void accessResource() {
        lock.lock();
        try {
            System.out.println("已经对资源进行了处理");
            if (lock.getHoldCount() < 5) {
                accessResource();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        accessResource();
    }
}
