package deadlock;

/**
 * @author 58212
 * @date 2019-11-07 22:20
 */
public class MustDeadLock implements Runnable {

    public static void main(String[] args) {
        MustDeadLock mustDeadLock0 = new MustDeadLock(1);
        MustDeadLock mustDeadLock1 = new MustDeadLock(0);
        new Thread(mustDeadLock0).start();
        new Thread(mustDeadLock1).start();
    }

    static Object r1 = new Object();
    static Object r2 = new Object();

    int lock;

    public MustDeadLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            synchronized (r1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (r2) {
                    System.out.println(Thread.currentThread().getName() + "get r2");
                }
            }
        } else if (lock == 0) {
            synchronized (r2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (r1) {
                    System.out.println(Thread.currentThread().getName() + "get r1");
                }
            }
        }
    }
}
