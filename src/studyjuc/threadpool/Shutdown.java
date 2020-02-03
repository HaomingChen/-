package studyjuc.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 58212
 * @date 2020-02-03 23:24
 */
public class Shutdown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(500);
        //返回队列中未被执行的任务
        List<Runnable> runnable = executorService.shutdownNow();
        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println("Await termination: " + executorService.awaitTermination(3L, TimeUnit.SECONDS));
        Thread.sleep(15000);
        System.out.println(executorService.isShutdown());
        System.out.println("Is terminated: " + executorService.isTerminated());
        System.out.println("Await termination: " + executorService.awaitTermination(3L, TimeUnit.SECONDS));
//        executorService.execute(new ShutDownTask());
    }
}

class ShutDownTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " :被中断了");
        }
    }
}
