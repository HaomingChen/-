package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author Haoming Chen
 * Created on 2019/10/22
 */
public class Id {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getId());
        new Thread(() -> {
            System.out.println(Thread.currentThread().getId());
        }).start();
    }
}
