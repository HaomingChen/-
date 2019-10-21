package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 利用synchronize关键字打印100以内的奇数偶数
 *
 * @author Haoming Chen
 * Created on 2019/10/18
 */
public class EvenOddPrintSync {

    static volatile int count;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new PrintEvenNum());
        Thread threadB = new Thread(new PrintOddNum());
        threadA.start();
        threadB.start();
    }

}

class PrintEvenNum implements Runnable {

    @Override
    public void run() {
        while (EvenOddPrintSync.count < 100) {
            //当该线程读取EvenOddPringSync.count时
            //EvenOddPringSync.count为99小于100
            //此时该线程被阻塞
            synchronized (EvenOddPrintSync.lock) {
                if ((EvenOddPrintSync.count & 1) == 0) {
                    System.out.println("偶数: " + EvenOddPrintSync.count++);
                }
            }
        }
    }

}

class PrintOddNum implements Runnable {

    @Override
    public void run() {
        while (EvenOddPrintSync.count < 100) {
            synchronized (EvenOddPrintSync.lock) {
                if ((EvenOddPrintSync.count & 1) == 1) {
                    System.out.println("奇数: " + EvenOddPrintSync.count++);
                }
            }
        }
    }

}