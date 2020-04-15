package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 对比LongAdder和AtomicLong的效率
 *
 * @author Haoming Chen
 * Created on 2020/4/15
 */
public class LongAdderDemo {

    static AtomicLong aLong = new AtomicLong();
    static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new AddAtomicLong());
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        Long end = System.currentTimeMillis();
        System.out.println("Atomic Long costs " + (end - start) + " milliseconds");
        System.out.println("Atomic Long result " + LongAdderDemo.aLong.get() + "\n");

        ExecutorService executorService2 = Executors.newFixedThreadPool(20);
        Long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService2.submit(new AddLongAdder());
        }
        executorService2.shutdown();
        while (!executorService2.isTerminated()) {

        }
        Long end1 = System.currentTimeMillis();

        System.out.println("Long Adder costs " + (end1 - start1) + " milliseconds");
        System.out.println("Long Adder result " + LongAdderDemo.longAdder.sum());
    }

    public static class AddAtomicLong implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                aLong.getAndAdd(1L);
            }
        }

    }

    public static class AddLongAdder implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                longAdder.increment();
            }
        }

    }

}
