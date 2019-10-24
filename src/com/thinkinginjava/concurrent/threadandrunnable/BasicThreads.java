package com.thinkinginjava.concurrent.threadandrunnable;

/**
 * @author 58212
 * @date 2019-10-24 23:40
 */
public class BasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new LiftOff());
            t.start();
            System.out.println("Waiting for LiftOff");
        }
    }
}
