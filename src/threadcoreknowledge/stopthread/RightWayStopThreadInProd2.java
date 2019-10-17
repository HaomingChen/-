package threadcoreknowledge.stopthread;

/**
 * 恢复中断
 *
 * @author Haoming Chen
 * Created on 2019/10/16
 */
public class RightWayStopThreadInProd2 implements Runnable {

    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("GO");
            throwInMethod();
        }
    }

    private void throwInMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

}
