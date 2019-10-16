package threadcoreknowledge.stopthread;

/**
 * 描述: 如果在执行过程中,每次循环都会调用sleep或wait等方法
 * @author Haoming Chen
 * Created on 2019/10/16
 */
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 300000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
    }

}
