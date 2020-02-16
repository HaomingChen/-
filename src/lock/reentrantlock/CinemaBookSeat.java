package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Haoming Chen
 * Created on 2020/2/16
 */
public class CinemaBookSeat {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> CinemaBookSeat.bookSet()).start();
        new Thread(() -> CinemaBookSeat.bookSet()).start();
        new Thread(() -> CinemaBookSeat.bookSet()).start();
        new Thread(() -> CinemaBookSeat.bookSet()).start();
    }

    private static void bookSet() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始预定座位");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "完成预定座位");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
