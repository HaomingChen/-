# JavaThreadCourse

1. 并发工具类类别
    a) 为了并发安全: 互斥同步(锁)，非互斥同步(原子类)，无同步方案(ThreadLocal)
    b) 管理线程、提高效率
    c) 线程协作
2. 线程池
创建线程 -> TCB -> 栈, 程序计数器, 本地方法栈 
为什么要有线程池: 反复创建线程开销大， 过多的线程需要占用内存
让线程始终保持工作 -> 避免了一部分创建和销毁线程带来的开销
线程池创建线程的核心思想是能不创建线程就不创建线程(核心线程)

3. 线程池队列:
1) 直接交接: SynchronousQueue -> DUAL_STACK
2) 无界队列: LinkedBlockingQueue size = Integer.MAX_VALUE
3) 有界的队列: ArrayBlockingQueue

4. 线程池线程设定规则
1) CPU密集型(加密、计算hash等): 最佳线程数为CPU核心数的1~2倍左右
2) 耗时IO型(读写数据库,文件,网络读写等)，以JVM线程监控显示繁忙为依据，保证线程空闲可以衔接上
3) 线程数 = CPU核心数*(1 + 平均等待时间/平均工作时间)

5. 停止线程的方法
1) shutdownNow -> 为对所有在运行的线程发送interrupted标记, 清空LinkedBlockingQueue
2) shutdown -> 拒绝新任务加入队列 
3) awaitTerminated -> 在规定时间内检测线程池是否关闭
4) isShutDown -> 任务队列是否关闭
5) isTerminated -> 线程池是否关闭

6. 线程任务拒绝策略
AbortPolicy: 直接丢弃任务并抛出异常
DiscardPolicy: 直接丢弃任务且不抛出异常
DiscardOldestPolicy: 丢弃任务队列头部的任务
CallerRunsPolicy: 将任务交由提交任务的线程执行 -> 负反馈机制

7. 线程池线程重复运行的机制
线程池通过addWorker((Runnable)Task)方法来添加工作线程
线程池通过运行工作线程的worker方法来启动线程, worker方法会启动一个while循环
在while循环中获取任务队列头的任务(Runnable)Task且执行

8. 线程池提供的钩子方法 preExecute() -> 可以在线程池运行之前添加逻辑从而
自定义线程池的功能

9. 线程池的状态
Running: 接受新任务并处理排队任务
Shutdown: 不接受新任务,但处理排队任务
Stop: 不接受新任务，也不处理排队任务，并中断正在 进行的任务
Tidying: 所有任务都已终止， work count为0，线程转换到tidying状态，
并运行terminate()钩子方法。
terminated：terminate()运行完成

10. ThreadLocal的使用场景:
 1.每个线程需要一个独享的对象(通常是工具类，典型需要使用的类有SimpleDateFormat和Random)
 2.每个线程内需要保存全局变量(例如在拦截器中获取用户信息), 可以让不同方法直接使用, 避免
 参数传递的麻烦
 什么时候用initialValue的ThreadLocal包装类？一般用来初始化工具类, ThreadLocal的值的创建受服务器控制
 什么时候用set的ThreadLocal包装类？ 无法预知何时创建Threadlocal, 例如: 用户登录, 需要多个方法共享
 用户登录数据时
 思考: 按照粒度来分: 局部变量的作用域一般为方法内, 线程不共有, 在每个线程创建的时候就会创建
 一般线程安全, 但是浪费资源。 ThreadLocal的作用于为线程内, 线程不共有， 故线程安全。静态变量
 整个java程序共有, 线程共有，所以可能发生线程不安全的情况。 应按照使用场景来合理使用这三种变量。
 Spring中的Threadlocal: RequestContextHolder
 ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest() 
 
11. 达成死锁的条件
互斥: 进程对所分配到的资源不允许其他进程进行访问，若其他进程访问该资源，只能等待，直至占有该资源的进程使用完成后释放该资源
(我得到锁, 你就不能有锁)
请求和保持条件: 进程获得一定的资源之后，又对其他资源发出请求，但是该资源可能被其他进程占有，此事请求阻塞，但又对自己获得的资源保持不放
(我想要获得锁的时候如果得不到就一直保持对锁的请求)
实例: tryLock()方法破坏了请求保持条件, 使得程序发生死锁的概率降低

不可剥夺条件: 是指进程已获得的资源，在未完成使用之前，不可被剥夺，只能在使用完后自己释放
(其它线程无法剥夺已经获得锁的线程)
环路等待条件：是指进程发生死锁后，必然存在一个进程--资源之间的环形链
(循环持有锁)

happens-before八大原则
1. 程序次序规则: 一个线程内，按照代码顺序，书写在前面的操作先行发生与书写在后面的操作。
2. 锁定规则: 一个unlock操作先行发生于后面对同一个锁的lock操作
3. volatile变量规则: 对一个变量的写操作先行发生于后面对这个变量的读操作
4. 传递规则: 如果操作A先行发生于操作B, 而操作B又先行发生于操作C，则可以得出操作A
先行发生于操作C
5. 线程启动规则: Thread对象的start()方法先行发生于此线程的每一个动作
6. 线程中断规则: 对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生
解释: A线程调用了B线程的interrupt方法当B线程检测到中断事件的发生时A线程在调用interrupt方法前做的所有操作对线程b是可见的
7. 线程终结规则： 线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join()方法
结束、Thread.isAlive()的返回值手段检测到线程已经终止执行
解释: A线程调用了b线程的join方法 在b线程中止后其对共享变量的所有操作对a线程都是可见的
8. 对象终结规则: 一个对象的初始化完成先行发生于他的finalize()方法的开始

12. lock锁的最佳实践: locker.lock;
                       try{
                            ... do something
                       }finally{
                            locker.unlock;
                       }
13. synchronize: 悲观锁(高并发, 线程竞争激烈) CAS:乐观锁
14. git -> CAS乐观锁
15. 排他锁: 独占锁 共享锁: 读锁
16. ReentrantReadWriteLock非公平锁的实现策略: 读锁可以直接尝试抢锁, 写锁将检查队列的头部, 若头部不为持有读锁的线程, 
则可以抢锁。 原因: 读锁可以为多个获取读锁的线程共享。 若在非公平锁的状态下。 读锁插队不断获取锁将会造成写锁的饥饿。