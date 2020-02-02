package studyjuc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author 58212
 * @date 2020-02-02 15:28
 */
public class EveryTaskOneThread {

    public static void main(String[] args) {
        Task task = new Task();
        for(int i = 0; i < 10; i++) {
            new Thread(task).start();
        }
        Executors.newFixedThreadPool(1);
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Run Task: " + Thread.currentThread().getName());
        }
    }
}
