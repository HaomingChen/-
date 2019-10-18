package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 利用synchronize关键字打印100以内的奇数偶数
 *
 * @author Haoming Chen
 * Created on 2019/10/18
 */
public class EvenOddPrintSync {

    static volatile int count;

    public static void main(String[] args) {
        Thread threadA = new Thread(new printEvenNum());
        Thread threadB = new Thread(new printOddNum());
        threadA.start();
        threadB.start();
    }

}

class printEvenNum implements Runnable {
    @Override
    public void run() {
        while (EvenOddPrintSync.count < 100) {
            synchronized (EvenOddPrintSync.class) {
                if ((EvenOddPrintSync.count & 1) == 0) {
                    System.out.println("偶数" + EvenOddPrintSync.count++);
                }
            }
        }
    }
}

class printOddNum implements Runnable {
    @Override
    public void run() {
        while (EvenOddPrintSync.count < 100) {
            synchronized (EvenOddPrintSync.class) {
                if ((EvenOddPrintSync.count & 1) == 1) {
                    System.out.println("奇数" + EvenOddPrintSync.count++);
                }
            }
        }
    }
}