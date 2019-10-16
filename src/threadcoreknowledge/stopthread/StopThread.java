package threadcoreknowledge.stopthread;

/**
 * 使用stop停止线程
 * @author Haoming Chen
 * Created on 2019/10/16
 */
public class StopThread implements Runnable {

    @Override
    public void run() {

        for (int i = 1; i < 11; i++) {
            System.out.println(i + "连发放武器");
            for (int j = 1; j < 6; j++) {
                System.out.println(i + "连" + j + "号士兵获得武器");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StopThread stopThread = new StopThread();
        Thread thread = new Thread(stopThread);
        thread.start();
        Thread.sleep(5500);
        thread.stop();
    }

}
