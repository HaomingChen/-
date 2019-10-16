package threadcoreknowledge.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用interrupt停止线程
 *
 * @author Haoming Chen
 * Created on 2019/10/16
 */
public class WrongWayVolatileFixed {
    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        threadcoreknowledge.stopthread.volatiledemo.Producer producer = new threadcoreknowledge.stopthread.volatiledemo.Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        threadcoreknowledge.stopthread.volatiledemo.Consumer consumer = new threadcoreknowledge.stopthread.volatiledemo.Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(storage.take() + "被消费了");
            Thread.sleep(1000);
        }
        System.out.println("消费者不需要更多数据了");
        producerThread.interrupt();

    }

    class Producer implements Runnable {

        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 10000000) {
                    if (num % 100 == 0 && !Thread.currentThread().isInterrupted()) {
                        storage.put(num);
                        System.out.println(num + "是100的倍数, 被放入仓库中了");
                    }
                    num++;
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者结束运行");
            }
        }
    }

    class Consumer {
        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            if (Math.random() > 0.95) {
                return false;
            }
            return true;
        }
    }

}