package threadcoreknowledge.stopthread;

/**
 * run方法内没有sleep或wait方法时,停止线程
 *
 * @author Haoming Chen
 * Created on 2019/10/15
 */
public class RightWayStopThreadWithoutSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int i = 0;
            while (!Thread.currentThread().isInterrupted() && i < Integer.MAX_VALUE / 2) {
                if (i % 10000 == 0) {
                    System.out.println(i);
                }
                i++;
            }
            System.out.println("任务运行结束了");
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
