package com.imooc.concurrent.transformers;

public class EnergySystemTest {
    //将要构建的能量世界中能量盒子数量
    public static final int BOX_AMOUNT = 100;
    //每个盒子初始能量
    public static final double INITIAL_ENERGY = 1000;

    public static void main(String[] args) {
        //初始化共享的能量世界
        EnergySystem eng = new EnergySystem(BOX_AMOUNT, INITIAL_ENERGY);
        //开启100个线程 分别从第1 2 3 4 5 6 ... 100个盒子不断转移能量到其它盒子
        for(int i = 0; i < BOX_AMOUNT; i++){
            EnergyTransferTask task = new EnergyTransferTask(eng, i, INITIAL_ENERGY);
            Thread t = new Thread(task,"TransferThread_" + i);
            t.start();
        }
    }
}
