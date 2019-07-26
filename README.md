# JavaThreadCourse
后续扩展知识
Java Memory Mode
JMM描述了Java线程如何通过内存进行交互
happens-before原则
synchronized,volatile&final 了解这些关键字如何实现happens-before原则

Locks & Condition对象 Java锁机制和等待条件的高层实现->>Java5.0后引入java.util.concurrent.locks
了解java如何实现对线程加锁以及同步通信

线程安全性
原子性,可见性
java.util.concurrent.atomic避免原子性编程问题
如何通过synchronized实现原子性操作
如何通过synchronized&volatile实现可见性编程
了解DeadLocks

多线程常用交互模型
Producer-Consumer模型 制造者-消费者模型 https://www.runoob.com/html/thread-procon.html
Read-Write Lock模型
Future模型
Worker Thread模型
了解Java并发包中哪些类实现了以上模型

Java5并发编程工具
java.util.concurrent
线程池ExecutorService
Callable&Future
BlockingQueue

推荐阅读
Java Concurrency In Practice 
