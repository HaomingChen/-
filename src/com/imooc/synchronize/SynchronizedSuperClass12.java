package com.imooc.synchronize;

/**
 * @author Haoming Chen
 * Created on 2019/10/8
 * <p>
 * 验证synchronized关键字的可重入粒度
 */
public class SynchronizedSuperClass12 {
    public synchronized void method1() {
        System.out.println("super class method");
    }
}

class SynchronizedClass extends SynchronizedSuperClass12 {

    @Override
    public synchronized void method1() {
        System.out.println("overrid child method");
        super.method1();
    }

    public static void main(String[] args) {
        SynchronizedClass synchronizedClass = new SynchronizedClass();
        synchronizedClass.method1();
    }
}
