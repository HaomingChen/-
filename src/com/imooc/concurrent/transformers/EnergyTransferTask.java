package com.imooc.concurrent.transformers;

public class EnergyTransferTask implements Runnable{
    //共享的能量世界
    private EnergySystem energySystem;
    //能量转移的源能量盒子下标
    private int fromBox;
    //单次能量转移的最大单元
    private double maxAmount;
    //最大休眠时间(毫秒)
    private int DELAY = 10;

    public EnergyTransferTask(EnergySystem energySystem, int from, double max){
        this.energySystem = energySystem;
        this.fromBox = from;
        this.maxAmount = max;
    }

    public void run(){
        try{
            while(true){
                //随机一个能量转移目的地的盒子的index
                int toBox = (int)(energySystem.getBoxAmount()*Math.random());
                //随机一个能量转移数目
                double amount = maxAmount * Math.random();
                //开始能量转移
                energySystem.transfer(fromBox,toBox,amount);
                //调用sleep阻塞线程
                Thread.sleep((int)(DELAY*Math.random()));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
