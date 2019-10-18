package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 3个线程 1和2首先被阻塞 3唤醒线程
 * start()先执行不代表线程启动
 *
 * @author Haoming Chen
 * Created on 2019/10/18
 */
public class WaitNotifyAll implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getState());
        synchronized (this) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " has got the lock");
                this.wait();
                System.out.println(Thread.currentThread().getName() + " has waken up");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyAll waitNotifyAll = new WaitNotifyAll();
        Thread threadA = new Thread(waitNotifyAll);
        Thread threadB = new Thread(waitNotifyAll);
        threadA.start();
        Thread.sleep(100);
        threadB.start();
        Thread.sleep(100);
        System.out.println(threadB.getName() + "-" + threadB.getState());
        Thread.sleep(5000);
        new Thread(() -> {
            synchronized (waitNotifyAll) {
                System.out.println(Thread.currentThread().getName() + " has got the lock");
                waitNotifyAll.notify();
//                waitNotifyAll.notifyAll();
            }
        }).start();
    }

}


