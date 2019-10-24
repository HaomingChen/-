package com.thinkinginjava.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 58212
 * @date 2019-10-24 23:54
 */
public class CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
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
