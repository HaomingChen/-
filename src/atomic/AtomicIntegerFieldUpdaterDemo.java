package atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater操作
 * static变量无效
 * 非public变量无效 -> getDeclaredField
 *
 * @author Haoming Chen
 * Created on 2020/4/15
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable {

    Candidate candidate;

    class Candidate {
        volatile int score;
    }

    public AtomicIntegerFieldUpdater<Candidate> updater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterDemo aifd = new AtomicIntegerFieldUpdaterDemo();
        Thread t1 = new Thread(aifd);
        Thread t2 = new Thread(aifd);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(aifd.candidate.score);
    }

    public AtomicIntegerFieldUpdaterDemo() {
        candidate = new Candidate();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            updater.getAndAdd(candidate, 1);
        }
    }
}
