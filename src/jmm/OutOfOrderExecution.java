package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 描述: 演示重排序的现象
 * "直到达到某个条件才停止", 测试小概率事件
 *
 * @author Haoming Chen
 * Created on 2019/10/29
 */
public class OutOfOrderExecution {

    private static int x, y, a, b = 0;

    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            //发生重排序的状况
            //b = c在a = 5之前运行
            Thread threadA = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });
            Thread threadB = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });
            threadA.start();
            threadB.start();
            countDownLatch.countDown();
            threadB.join();
            threadA.join();
            if (x == 0 && y == 0) {
                System.out.println("x" + x + " y" + y);
                break;
            }
        }
    }
}
