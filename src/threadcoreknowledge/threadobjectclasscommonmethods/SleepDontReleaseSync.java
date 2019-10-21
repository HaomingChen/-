package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * sleep不释放sync的monitor
 *
 * @author Haoming Chen
 * Created on 2019/10/21
 */
public class SleepDontReleaseSync {
    public static void main(String[] args) {
        TestSleepDontReleaseSync sleepDontReleaseLock = new TestSleepDontReleaseSync();
        Thread threadA = new Thread(sleepDontReleaseLock);
        Thread threadB = new Thread(sleepDontReleaseLock);
        threadA.start();
        threadB.start();
    }
}

class TestSleepDontReleaseSync implements Runnable {
    @Override
    public void run() {
        try {
            sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void sync() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "线程获得了锁");
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + "线程释放了锁");
    }
}
