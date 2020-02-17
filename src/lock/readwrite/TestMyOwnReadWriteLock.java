package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写一个自己的读写锁
 *
 * @author Haoming Chen
 * Created on 2020/2/17
 */
public class TestMyOwnReadWriteLock {

    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
    static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> read(), "read " + i);
        }
        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> write(), "Write1").start();
    }

    public static void read() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": I gotta read something");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": I gotta write something");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

}
