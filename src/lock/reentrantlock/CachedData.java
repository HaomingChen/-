package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 实际锁降级的例子
 *
 * @author Haoming Chen
 * Created on 2020/2/19
 */
public class CachedData {
    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        //最开始是读取
        rwl.readLock().lock();
        if (!cacheValid) {
            //缓存失效,需要写入，所以需要获取写锁
            //释放读锁再获取写锁, 因为锁无法升级
            rwl.readLock().unlock();
            //获取到写锁
            rwl.writeLock().lock();
            try {
                //这里需要再次判断数据的有效性, 因为在我们释放读锁和获取写锁的空隙之内，可能有其它线程修改了数据。
                if (!cacheValid) {
                    //写入
                    data = new Object();
                    cacheValid = true;
                }
                //获取读锁 -> 锁降级
                rwl.readLock().lock();
            } finally {
                //释放了写锁, 但是依然持有读锁，这样一来，就可以多个线程同时读取了,提高了整体效率
                rwl.writeLock().unlock();
            }
        }

        try {
            System.out.println(data);
        } finally {
            //释放读锁
            rwl.readLock().unlock();
        }
    }

}
