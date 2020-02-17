package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示非公平和公平的读写锁策略
 *
 * @author Haoming Chen
 * Created on 2020/2/17
 */
public class NonfairBargeDemo {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁, 正在读取");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁, 正在写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> read(), "Read1").start();
        new Thread(() -> read(), "Read2").start();
        new Thread(() -> write(), "Write1").start();
        //下一个读锁无法插队
        new Thread(() -> read(), "Read2").start();
        new Thread(() -> write(), "Write2").start();
        new Thread(() -> write(), "Write3").start();
    }

}
