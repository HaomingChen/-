package studyjuc.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示每个任务前后放钩子函数
 *
 * @author Haoming Chen
 * Created on 2020/2/6
 */
public class PausedThreadPool extends ThreadPoolExecutor {

    private boolean isPaused;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private Condition unpaused = reentrantLock.newCondition();

    public PausedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public PausedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public PausedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public PausedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        reentrantLock.lock();
        try {
            while (isPaused) {
                unpaused.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void paused() {
        reentrantLock.lock();
        try {
            isPaused = true;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void resume() {
        reentrantLock.lock();
        try {
            isPaused = false;
            unpaused.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PausedThreadPool pausedThreadPool = new PausedThreadPool(10, 20,
                10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executed");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 10000; i++) {
            pausedThreadPool.execute(runnable);
        }
        Thread.sleep(1500);
        pausedThreadPool.paused();
        System.out.println("Thread pool has been paused");
        Thread.sleep(1500);
        System.out.println("Thread pool has been restored");
        pausedThreadPool.resume();
    }
}
