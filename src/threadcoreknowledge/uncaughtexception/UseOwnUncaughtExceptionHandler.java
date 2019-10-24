package threadcoreknowledge.uncaughtexception;

/**
 * @author Haoming Chen
 * Created on 2019/10/22
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));
        Thread threadA = new Thread(new UseOwnUncaughtExceptionHandler(), "myThread1");
        threadA.start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "myThread2").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "myThread3").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "myThread4").start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
