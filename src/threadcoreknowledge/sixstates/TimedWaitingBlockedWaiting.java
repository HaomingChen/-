package threadcoreknowledge.sixstates;

/**
 * 展示线程的TIMED_WAITING,BLOCKED,WAITING状态
 *
 * @author Haoming Chen
 * Created on 2019/10/17
 */
public class TimedWaitingBlockedWaiting implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        TimedWaitingBlockedWaiting runnable = new TimedWaitingBlockedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
        Thread.sleep(2000);
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
