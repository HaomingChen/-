package studyjuc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 58212
 * @date 2020-02-03 22:36
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new EveryTaskOneThread.Task());
        }
    }
}
