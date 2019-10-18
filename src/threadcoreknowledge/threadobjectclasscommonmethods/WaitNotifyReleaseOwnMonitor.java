package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 证明wait()只释放当前monitor
 *
 * @author Haoming Chen
 * Created on 2019/10/18
 */
public class WaitNotifyReleaseOwnMonitor implements Runnable {

    Object resourceA = new Object();
    Object resourceB = new Object();

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + " gets resourceA");
            synchronized (resourceB) {
                System.out.println(Thread.currentThread().getName() + " gets resourceB");
                System.out.println(Thread.currentThread().getName() + " release resourceA");
                try {
                    resourceA.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyReleaseOwnMonitor waitNotifyReleaseOwnMonitor = new WaitNotifyReleaseOwnMonitor();
        Thread threadA = new Thread(waitNotifyReleaseOwnMonitor);
        Thread threadB = new Thread(waitNotifyReleaseOwnMonitor);
        threadA.start();
        Thread.sleep(100);
        threadB.start();
    }
}
