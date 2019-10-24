package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author Haoming Chen
 * Created on 2019/10/22
 */
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": start running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": stop running");
        });
        System.out.println(Thread.currentThread().getName() + ": 主线程开始运行");
        thread.start();
//        thread.join();
        synchronized (thread) {
            //使主线程进入waiting状态
            //当thread线程run方法执行完毕后, 会调用notifyAll方法
            thread.wait();
        }
        System.out.println(Thread.currentThread().getName() + ": 主线程运行完毕");
    }
}
