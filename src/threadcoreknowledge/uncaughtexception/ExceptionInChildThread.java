package threadcoreknowledge.uncaughtexception;

/**
 * 描述: 单线程，抛出，处理，有异常堆栈
 * 多线程，子线程发生异常，会有什么不同？
 *
 * @author Haoming Chen
 * Created on 2019/10/22
 */
public class ExceptionInChildThread implements Runnable {

    //在子线程中抛出的异常难以被发现
    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
