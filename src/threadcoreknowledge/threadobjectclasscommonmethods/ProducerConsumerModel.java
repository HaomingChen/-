package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.LinkedList;

/**
 * 使用wait()/notify实现生产消费者模式
 *
 * @author Haoming Chen
 * Created on 2019/10/18
 */
public class ProducerConsumerModel {

    public static void main(String[] args) {

        EventStorage eventStorage = new EventStorage(10);
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();

    }

}

class Producer implements Runnable {

    private EventStorage storage;

    public Producer(
            EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                storage.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Consumer implements Runnable {

    private EventStorage storage;

    public Consumer(
            EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                storage.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class EventStorage {

    LinkedList<Date> storage;
    int capacity;

    public EventStorage(int capacity) {
        this.storage = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void take() throws InterruptedException {
        while (storage.size() == 0) {
            wait();
        }
        System.out.println("拿到了" + storage.poll());
        notify();
    }

    public synchronized void put() throws InterruptedException {
        while (storage.size() == capacity) {
            wait();
        }
        System.out.println("仓库里有了" + storage.add(new Date()));
        notify();
    }

}
