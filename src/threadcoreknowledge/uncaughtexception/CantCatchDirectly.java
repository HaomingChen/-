package threadcoreknowledge.uncaughtexception;

/**
 * 描述:
 * 1. 不加try catch抛出四个异常，都带线程名字
 * 2. 加了try catch, 期望捕获到第一个线程的异常，线程234不应运行，希望看到打印出Caught Exception
 * 3. 执行时发现，根本没Caught Exception线程2，3，4依然运行且抛出异常
 * 说明线程的异常不能用传统方法捕获
 *
 * @author Haoming Chen
 * Created on 2019/10/22
 */
public class CantCatchDirectly implements Runnable {

    public static void main(String[] args) {

        Thread thread = new Thread(new CantCatchDirectly());
        thread.start();
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

}
