package deadlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 哲学家就餐
 *
 * @author Haoming Chen
 * Created on 2019/11/12
 */
public class PhilosophyDining {

    static class Phiolsophy implements Runnable {

        Object leftChopstick;
        Object rightChopStick;

        public Phiolsophy(Object leftChopstick, Object rightChopStick) {
            this.leftChopstick = leftChopstick;
            this.rightChopStick = rightChopStick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    synchronized (leftChopstick) {
                        doAction("Left Chopstick picked up");
                        synchronized (rightChopStick) {
                            doAction("Right chopstick picked up...eating");
                            doAction("I'm already full, put down right chopstick");
                        }
                        doAction("put down left chopstick");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + ": " + action);
            Thread.sleep((long) (Math.random() * 10));
        }

    }

    public static void main(String[] args) {
        Object[] chopsticks = new Object[5];
        Phiolsophy[] philosophy = new Phiolsophy[chopsticks.length];
        //初始化锁资源: 5个筷子
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }
        //初始化哲学家
        for (int i = 0; i < chopsticks.length; i++) {
            //通过破坏造成死锁的必要条件-环路,解决死锁问题
            if (i == chopsticks.length - 1) {
                //交换哲学家获得锁的顺序从而破坏环路
                philosophy[i] = new Phiolsophy(chopsticks[(i + 1) % chopsticks.length], chopsticks[i]);
            } else {
                philosophy[i] = new Phiolsophy(chopsticks[i], chopsticks[(i + 1) % chopsticks.length]);
            }
            new Thread(philosophy[i], "哲学家" + i + "号start").start();
        }
    }
}
