package com.imooc.concurrent.base;

public class Stage extends Thread{

    @Override
    public void run() {
        System.out.println("欢迎观看隋唐演义");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The war has begun");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sui start a fight with peasants");
        ArmyRunnable armyTaskOfSuiDynasty = new ArmyRunnable();
        ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();

        //使用Runnable接口创建线程
        Thread armyOfSuiDynasty = new Thread(armyTaskOfSuiDynasty,"隋军");
        Thread armyOfRevolt = new Thread(armyTaskOfRevolt,"农民起义军");
        //启动线程，让军队开始作战
        armyOfSuiDynasty.start();
        armyOfRevolt.start();

        try{
            Thread.sleep(50);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("程咬金进场输出》》》》》》》》》》》》》》》》》》》》");
        Thread mrCheng = new KeyPersonThread();
        mrCheng.setName("程咬金");
        armyTaskOfRevolt.keepRunning = false;
        armyTaskOfSuiDynasty.keepRunning = false;
        System.out.println("QQQQQQWWWWWWRRRRRRRRRRR》》》》》》》》》》》》》》》》》》》》");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        mrCheng.start();
        //join: 调用线程将会等待该线程执行完毕
        try {
            mrCheng.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The was is over");
    }

    public static void main(String[] args) {
        new Stage().start();
    }
}
