package threadcoreknowledge.sixstates;

/**
 * 展示线程的NEW,RUNNABLE,Terminated状态
 * 即使是正在运行,也是Runnable状态
 *
 * @author Haoming Chen
 * Created on 2019/10/17
 */
public class NewRunnableTerminated implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new NewRunnableTerminated());
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(10);
        //主线程在thread线程运行时调用getState()打印出getState()方法
        System.out.println(thread.getState());
        Thread.sleep(2000);
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }

}
