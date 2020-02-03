package studyjuc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 58212
 * @date 2020-02-03 23:00
 */
public class ScheduleAndCachedThreadPool {

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        scheduledThreadPool.schedule(new Task2(), 5, TimeUnit.SECONDS);
    }

    static class Task2 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
