package threadcoreknowledge.stopthread;

/**
 * 最佳实践: catch了InterruptedException滞后的优先选择: 在方法签名中抛出异常那么
 * run()就会强制try/catch
 * @author Haoming Chen
 * Created on 2019/10/16
 */
public class RightWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("GO");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                //保存日志 - 停止程序
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

}
