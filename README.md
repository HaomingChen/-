# JavaThreadCourse

1. 并发工具类类别
    a) 为了并发安全: 互斥同步(锁)，非互斥同步(原子类)，无同步方案(ThreadLocal)
    b) 管理线程、提高效率
    c) 线程协作
2. 线程池
为什么要有线程池: 反复创建线程开销大， 过多的线程需要占用内存
让线程始终保持工作 -> 避免了一部分创建和销毁线程带来的开销
线程池创建线程的核心思想是能不创建线程就不创建线程(核心线程)

3. 线程池队列:
1) 直接交接: SynchronousQueue
2) 无界队列: LinkedBlockingQueue
3) 有界的队列: ArrayBlockingQueue