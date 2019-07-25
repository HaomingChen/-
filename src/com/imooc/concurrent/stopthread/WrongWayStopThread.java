package com.imooc.concurrent.stopthread;

public class WrongWayStopThread extends Thread{

    public static void main(String[] args) {
        WrongWayStopThread thread = new WrongWayStopThread();
        System.out.println("Starting thread...");
        thread.start();
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Interrupting thread...");
        thread.interrupt();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("application stops");
    }

    public void run(){
        while(true){
            System.out.println("Thread is running...");
            long time = System.currentTimeMillis();
            while(System.currentTimeMillis() - time < 1000){
                //减少屏幕输出的空循环
            }
        }
    }
}
