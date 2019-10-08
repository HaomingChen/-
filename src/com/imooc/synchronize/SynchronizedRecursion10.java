package com.imooc.synchronize;

/**
 * @author Haoming Chen
 * Created on 2019/10/8
 *
 * 验证synchronized关键字的可重入粒度
 */
public class SynchronizedRecursion10 {

    int times = 0;

    private synchronized void method1() {
        System.out.println("该方法被调用" + (times + 1) + "次.");
        if (times == 0) {
            times++;
            method1();
        }
    }

    public static void main(String[] args) {
        SynchronizedRecursion10 synchronizedRecursion10 = new SynchronizedRecursion10();
        synchronizedRecursion10.method1();
    }
}
