package interviewqs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 58212
 * @date 2020-01-12 19:33
 */
public class ConsumerProducerModule {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class Producer implements Runnable {
    private Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                storage.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                storage.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Storage {
    List<Date> storage;
    int size;

    public Storage() {
        this(10);
    }

    public Storage(int size) {
        this.size = size;
        storage = new LinkedList<>();
    }

    public synchronized void consumer() throws InterruptedException {
        while (storage.isEmpty()) {
            wait();
        }
        storage.remove(storage.size() - 1);
        System.out.println("仓库里有了" + storage.size() + "个产品。");
        notify();
    }

    public synchronized void producer() throws InterruptedException {
        while (storage.size() == size) {
            wait();
        }
        storage.add(new Date());
        System.out.println("仓库里拿了" + storage.size() + "个产品。");
        notify();
    }
}
