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


