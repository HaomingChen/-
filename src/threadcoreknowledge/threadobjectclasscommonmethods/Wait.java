package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 展示wait和notify的基本用法
 * 1. 研究代码执行顺序
 * 2. 证明wait释放锁
 *
 * @author Haoming Chen
 * Created on 2019/10/17
 */
public class Wait {

    public static Object object = new Object();

    static class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getState());
                try {
                    //WAITING
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": 线程A运行结束");
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                //唤醒WAITING, 不释放锁
                object.notify();
                System.out.println(Thread.currentThread().getName() + ": 线程B运行结束");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadA.start();
        Thread.sleep(100);
        System.out.println("Thread.getState()" + threadA.getState());
        threadB.start();
    }

}
