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
        EventStorage eventStorage = new EventStorage();
        Thread threadA = new Thread(new Consumer(eventStorage));
        Thread threadB = new Thread(new Producer(eventStorage));
        threadA.start();
        threadB.start();
    }

}

class EventStorage {

    LinkedList<Date> storage;
    public int capacity;

    public EventStorage() {
        this(10);
    }

    public EventStorage(int capacity) {
        storage = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void consume() throws InterruptedException {
        while (storage.isEmpty()) {
            wait();
        }
        System.out.println(storage.poll());
        notify();
    }

    public synchronized void produce() throws InterruptedException {
        while (storage.size() == capacity) {
            wait();
        }
        System.out.println("Add new Date");
        storage.add(new Date());
        notify();
    }
}

class Consumer implements Runnable {

    EventStorage eventStorage;

    public Consumer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                eventStorage.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer implements Runnable {

    EventStorage eventStorage;

    public Producer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                eventStorage.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

