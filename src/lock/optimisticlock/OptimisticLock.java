package lock.optimisticlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Haoming Chen
 * Created on 2020/2/16
 */
public class OptimisticLock {

    static AtomicInteger ai = new AtomicInteger();

    public static void main(String[] args) {
        ai.set(1);
        ai.incrementAndGet();
        System.out.println(ai.get());
    }
}
