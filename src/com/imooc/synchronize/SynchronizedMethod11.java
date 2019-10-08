package com.imooc.synchronize;

/**
 * @author Haoming Chen
 * Created on 2019/10/8
 *
 * 验证synchronized关键字的可重入粒度
 */
public class SynchronizedMethod11 {

    private synchronized void method1(){
        System.out.println("我是方法1");
        method2();
    }

    private synchronized void method2(){
        System.out.println("我是方法2");
    }

    public static void main(String[] args) {
        SynchronizedMethod11 synchronizedMethod11 = new SynchronizedMethod11();
        synchronizedMethod11.method1();
    }
}
