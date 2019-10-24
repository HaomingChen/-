package com.thinkinginjava.concurrent.threadandrunnable;

/**
 * @author 58212
 * @date 2019-10-24 23:30
 */
public class LiftOff implements Runnable {

    public static void main(String[] args) {
        LiftOff liftOff = new LiftOff();
        liftOff.run();
    }

    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff") + "), ";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            //建以线程调度器调度线程, 让出该线程的时间片
            Thread.yield();
        }
    }
}
