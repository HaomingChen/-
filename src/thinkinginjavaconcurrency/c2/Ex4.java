package thinkinginjavaconcurrency.c2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// concurrency/Ex4.java
// TIJ4 Chapter Concurrency, Exercise 4, page 1124
// Repeat Exercise 2 using the different types of executors shown in this section.
/**
 * @author 58212
 * @date 2019-11-24 23:50
 */
class Ex4FibonacciA implements Runnable {
    private int n = 0;

    public Ex4FibonacciA(int n) {
        this.n = n;
    }

    private int fib(int x) {
        if (x < 2) return 1;
        return fib(x - 2) + fib(x - 1);
    }

    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + " ");
        }
        System.out.println();
    }
}

class Ex4FibonacciB implements Runnable {

    private int n = 0;

    public Ex4FibonacciB(int n) {
        this.n = n;
    }

    private int fib(int x) {
        if (x < 2) {
            return 1;
        }
        return fib(x - 1) + fib(x - 2);
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + " ");
        }
        System.out.println();
    }
}

class Ex4FibonacciC implements Runnable {

    private int n = 0;

    public Ex4FibonacciC(int n) {
        this.n = n;
    }

    private int fib(int x) {
        if (x < 2) {
            return 1;
        }
        return fib(x - 1) + fib(x - 2);
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + " ");
        }
        System.out.println();
    }
}

class Ex4FibonacciD implements Runnable {

    private int n = 0;

    public Ex4FibonacciD(int n) {
        this.n = n;
    }

    private int fib(int x) {
        if (x < 2) {
            return 1;
        }
        return fib(x - 1) + fib(x - 2);
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + " ");
        }
        System.out.println();
    }
}

public class Ex4 {

    public static void main(String[] args) {
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        //有几个任务, 创建几个线程
        executorService1.execute(new Ex4FibonacciA(15));
        executorService1.execute(new Ex4FibonacciB(15));
        executorService1.execute(new Ex4FibonacciC(15));
        executorService1.execute(new Ex4FibonacciD(15));
        executorService1.shutdown();

        ExecutorService executorService2 = Executors.newFixedThreadPool(4);
        executorService2.execute(new Ex4FibonacciA(15));
        executorService2.execute(new Ex4FibonacciB(15));
        executorService2.execute(new Ex4FibonacciC(15));
        executorService2.execute(new Ex4FibonacciD(15));
        executorService2.shutdown();

        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        executorService3.execute(new Ex4FibonacciA(15));
        executorService3.execute(new Ex4FibonacciB(15));
        executorService3.execute(new Ex4FibonacciC(15));
        executorService3.execute(new Ex4FibonacciD(15));
        executorService3.shutdown();
    }
}
