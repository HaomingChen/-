package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Haoming Chen
 * Created on 2020/2/16
 */
public class GetHoldCount {
    static ReentrantLock lock  = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }
}
