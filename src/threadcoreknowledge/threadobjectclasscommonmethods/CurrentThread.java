package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author Haoming Chen
 * Created on 2019/10/22
 */
public class CurrentThread implements Runnable {

    public static void main(String[] args) {
        new CurrentThread().run();
        new Thread(new CurrentThread()).start();
        new Thread(new CurrentThread()).start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
