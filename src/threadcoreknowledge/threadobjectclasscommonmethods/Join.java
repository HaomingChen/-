package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 演示join, 注意语句的输出顺序
 *
 * @author Haoming Chen
 * Created on 2019/10/21
 */
public class Join {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });
        try {
            thread.start();
            thread2.start();
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程执行完毕");
    }

}
