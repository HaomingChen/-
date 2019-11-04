package jmm;

/**
 * @author Haoming Chen
 * Created on 2019/11/4
 */
public class NoVolatile2 implements Runnable{

    volatile boolean s = true;

    public static void main(String[] args) throws InterruptedException {
        NoVolatile2 noVolatile2 = new NoVolatile2();
        Thread threadA = new Thread(noVolatile2);
        Thread threadB = new Thread(noVolatile2);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println(noVolatile2.s);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            s = !s;
        }
    }

}
