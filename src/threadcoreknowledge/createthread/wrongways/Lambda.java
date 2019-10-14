package threadcoreknowledge.createthread.wrongways;

/**
 * @author Haoming Chen
 * Created on 2019/10/14
 */
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
