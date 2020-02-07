package threadlocal;

/**
 * ThreadLocal空指针异常
 *
 * @author Haoming Chen
 * Created on 2020/2/8
 */
public class ThreadLocalNPE {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    public long get() {
        //Long.parseLong("null"); <- 自动拆箱: 空指针异常
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        threadLocalNPE.set();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        }).start();
        System.out.println(threadLocalNPE.get());
    }
}
