package threadcoreknowledge.createthread;

/**
 * @author Haoming Chen
 * Created on 2019/10/14
 */
public class BothRunnableThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是通过Runnable实现的线程");
            }
        }) {
            @Override
            public void run() {
                System.out.println("我是通过Thread实现的线程");
            }
        }.start();
    }
}
