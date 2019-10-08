package com.imooc.synchronize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Haoming Chen
 * Created on 2019/10/8
 */
public class SynchronizedToLock13 {

    Lock lock = new ReentrantLock();

    private synchronized void method1(){
        System.out.println("我是synchronized关键字方法");
    }

    private void method2(){
        lock.lock();
        try{
            System.out.println("我是lock方法");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynchronizedToLock13 synchronizedToLock13 = new SynchronizedToLock13();
        synchronizedToLock13.method1();
        synchronizedToLock13.method2();
    }
}
