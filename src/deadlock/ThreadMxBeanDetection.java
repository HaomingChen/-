package deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * 用ThreadMXBean检测死锁
 *
 * @author 58212
 * @date 2019-11-11 16:24
 */
public class ThreadMxBeanDetection implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        ThreadMxBeanDetection mustDeadLock0 = new ThreadMxBeanDetection(1);
        ThreadMxBeanDetection mustDeadLock1 = new ThreadMxBeanDetection(0);
        new Thread(mustDeadLock0).start();
        new Thread(mustDeadLock1).start();
        Thread.sleep(1000);
        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        long[] threadArray = threadMxBean.findDeadlockedThreads();
        if (threadArray != null && threadArray.length != 0) {
            for (long id : threadArray) {
                System.out.println(threadMxBean.getThreadInfo(id));
            }
        }
    }

    static Object r1 = new Object();
    static Object r2 = new Object();

    int lock;

    public ThreadMxBeanDetection(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            synchronized (r1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (r2) {
                    System.out.println(Thread.currentThread().getName() + "get r2");
                }
            }
        } else if (lock == 0) {
            synchronized (r2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (r1) {
                    System.out.println(Thread.currentThread().getName() + "get r1");
                }
            }
        }
    }
}
