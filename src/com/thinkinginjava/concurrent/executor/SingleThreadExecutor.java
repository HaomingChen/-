package com.thinkinginjava.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 58212
 * @date 2019-10-25 0:05
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        //进入队列, 顺序运行
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exec.execute(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("Thread---" + Thread.currentThread().getName() + "---stop sleeping");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
    }
}
