package jmm;

/**
 * @author Haoming Chen
 * Created on 2019/11/4
 */
public class VolatileOk implements Runnable {

    volatile boolean s = true;

    public static void main(String[] args) throws InterruptedException {
        VolatileOk vola = new VolatileOk();
        Thread threadA = new Thread(vola);
        Thread threadB = new Thread(vola);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println(vola.s);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            s = true;
        }
    }

}

