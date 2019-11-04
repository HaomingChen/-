package jmm;

/**
 * @author Haoming Chen
 * Created on 2019/11/4
 */
public class NoVolatile implements Runnable {
    volatile int a = 1;

    public static void main(String[] args) throws InterruptedException {
        NoVolatile noVolatile = new NoVolatile();
        Thread threadA = new Thread(noVolatile);
        Thread threadB = new Thread(noVolatile);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println(noVolatile.a);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
        }
    }
}

