package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 利用synchronize关键字打印100以内的奇数偶数
 * 线程自旋 -> 浪费资源
 *
 * @author Haoming Chen
 * Created on 2019/10/18
 */
public class EvenOddPrintWaitNotify {

    static Object lock = new Object();

    static volatile int count;

    public static void main(String[] args) {
        Thread threadA = new Thread(new PrintNum());
        Thread threadB = new Thread(new PrintNum());
        threadA.start();
        threadB.start();
    }

}

class PrintNum implements Runnable {
    @Override
    public void run() {
        while (EvenOddPrintWaitNotify.count <= 100) {
            synchronized (EvenOddPrintWaitNotify.lock) {
                try {
                    EvenOddPrintWaitNotify.lock.notify();
                    System.out.println(Thread.currentThread().getName() + ":" + EvenOddPrintWaitNotify.count++);
                    EvenOddPrintWaitNotify.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
