package thinkinginjavaconcurrency.c1;
// concurrency/Ex1.java
// TIJ4 Chapter Concurrency, Exercise 1, page 1120
/** Implement a Runnable. Inside run(), print a message, and then call yield().
 * Repeat this three times, and then return from run(). Put a startup message in
 * the constructor and a shutdown message when the task terminates. Create a
 * number of these tasks and drive them using threads.
 **/

/**
 * @author 58212
 * @date 2019-11-24 23:17
 */

class Ex1RunnerA implements Runnable {
    public Ex1RunnerA() {
        System.out.println("Constructing Ex1RunnerA");
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hi from Ex1RunnerA");
            Thread.yield();
        }
        System.out.println("Ex1RunnerA task complete.");
        return;
    }
}

class Ex1RunnerB implements Runnable {
    public Ex1RunnerB() {
        System.out.println("Constructing Ex1RunnerB");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hi from Ex1RunnerB");
            Thread.yield();
        }
        System.out.println("Ex1RunnerB task complete");
        return;
    }
}

class Ex1RunnerC implements Runnable {
    public Ex1RunnerC() {
        System.out.println("Constructing Ex1RunnerC");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hi from Ex1RunnerC");
            Thread.yield();
        }
        System.out.println("Ex1RunnerC task complete.");
        return;
    }
}

public class Ex1 {
    public static void main(String[] args) {
        Thread ta = new Thread(new Ex1RunnerA());
        Thread tb = new Thread(new Ex1RunnerB());
        Thread tc = new Thread(new Ex1RunnerC());
        ta.start();
        tb.start();
        tc.start();
    }
}
