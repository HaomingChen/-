package thinkinginjavaconcurrency.streamfuture;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Haoming Chen
 * Created on 2020/1/14
 */
public class ParallelPrime {
    static final int COUNT = 100_000;
    AtomicInteger st = new AtomicInteger();
}
