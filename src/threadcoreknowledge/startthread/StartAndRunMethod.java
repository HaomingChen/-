package threadcoreknowledge.startthread;

/**
 * 调用run()和start()来启动线程
 *
 * @author Haoming Chen
 * Created on 2019/10/15
 */
public class StartAndRunMethod {

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread: - " + Thread.currentThread().getName() + " - starts.");
            }
        };

        runnable.run();

        new Thread(runnable){}.start();

    }

}
