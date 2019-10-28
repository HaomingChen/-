package com.thinkinginjava.concurrent.executor;

import java.util.concurrent.*;

/**
 * @author 58212
 * @date 2019-10-24 23:54
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(0, 5, 60L,
                TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
            exec.execute(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("Thread" + Thread.currentThread().getName() + "stop sleeping");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
    }
}
