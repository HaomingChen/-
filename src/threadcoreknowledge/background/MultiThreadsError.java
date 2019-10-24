package threadcoreknowledge.background;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Haoming Chen
 * Created on 2019/10/23
 */
public class MultiThreadsError implements Runnable {

    int index = 0;
    static MultiThreadsError instance = new MultiThreadsError();
    static AtomicInteger realIndex = new AtomicInteger();
    static int wrongCount = 0;
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
    boolean[] arr = new boolean[50000];

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上的结果是: " + instance.index);
        System.out.println("实际上进行了" + realIndex.get() + "次自增运算");
        System.out.println("错误的次数: " + wrongCount);
    }

    @Override
    public void run() {
        for (int i = 0; i < 15000; i++) {
            //当执行cyclicBarrier.await()的线程数达到设定的线程数时,
            //线程才会继续执行
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            //synchronize代码块线程可见
            //此处index读到的数字为其它线程修改过的数字
            //在此处线程被切换使得index被篡改, 会标记错误的index造成错误的次数变多
            synchronized (instance) {
                if (arr[index] && arr[index - 1]) {
                    System.out.println(i + "位发生错误");
                    wrongCount++;
                }
                //在代码运行到标记行之前, 线程也可能切换并进行计算, 造成错误的统计数值偏大
                arr[index] = true;
            }
        }
    }
}


