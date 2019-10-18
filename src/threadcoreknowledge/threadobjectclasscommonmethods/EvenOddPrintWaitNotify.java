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
        Thread threadA = new Thread(new printNum(), "偶数");
        Thread threadB = new Thread(new printNum(), "奇数");
        threadA.start();
        threadB.start();
    }

}

class printNum implements Runnable {
    @Override
    public void run() {
        while (EvenOddPrintWaitNotify.count <= 100) {
            synchronized (EvenOddPrintWaitNotify.lock) {
                EvenOddPrintWaitNotify.lock.notify();
                System.out.println(Thread.currentThread().getName() + ":" + EvenOddPrintWaitNotify.count++);
                try {
                    EvenOddPrintWaitNotify.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
