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


