package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 先join再mainThread.getState()
 * 通过debugger查看线程join前后对比
 *
 * @author Haoming Chen
 * Created on 2019/10/21
 */
public class JoinThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(mainThread.getState());
                System.out.println("Thread-0 运行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("等待子线程运行完毕");
        thread.join();
        System.out.println("子线程运行完毕");
    }
}
