package threadcoreknowledge.createthread;

/**
 * 用Thread方式实现线程
 *
 * @author Haoming Chen
 * Created on 2019/10/14
 */
public class ThreadStyle extends Thread {

    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }

    public static void main(String[] args) {
        //使用Thread类实现线程时, 想要新建一个新任务只能新建一个独立的线程
        //重写Thread线程中的run()方法
        Thread thread = new ThreadStyle();
        thread.start();
    }
}
