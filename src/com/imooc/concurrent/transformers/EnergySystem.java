package com.imooc.concurrent.transformers;

public class EnergySystem {
    //能量盒子,能量存储的地方 线程共享资源
    private final double[] energyBoxes;
    private final Object lockObj = new Object();
    /**
     *
     * @param n 能量盒子的数量
     * @param initialEnergy 每个能量盒子初始含有的能量值
     */
    public EnergySystem(int n, double initialEnergy){
        energyBoxes = new double[n];
        for(int i = 0; i< energyBoxes.length; i++){
            energyBoxes[i] = initialEnergy;
        }
    }

    /**
     * 能量的转移,从一个盒子到另一个盒子
     * @param from 能量源
     * @param to 能量终点
     * @param amount 能量值
     */
    public void transfer(int from, int to, double amount){
        //synchronized 字节码包含指令进入锁
        //monitorenter等待获取ObjectMonitor(获取对象monitor -> ObjectMonitor实现monitor)
        //和monitorexit释放ObjectMonitor
        //ObjectMonitor又有两条队列_WaitSet和_EntrySet用来保存ObjectWaiter对象
        //.wait()状态的线程处于_WaitSet队列中
        //持有monitor对象 -> 调用.wait() —>进入ObjectMonitor的_WaitSet队列
        //等待锁阻塞状态的线程处于_EntrySet队列
        //The Owner(Critical Section)指向获得ObjectMonitor对象的线程
        //ObjectWaiter为竞争锁的线程的封装
        synchronized (lockObj){
//            if(energyBoxes[from] < amount)
//                return;
            while(energyBoxes[from] <amount){
                try {
                    //调用wait必须持有monitor对象也就是获得锁
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
            energyBoxes[from] -= amount;
            //Note:在线程运行到此处时能量源盒子能量减少，若该线程耗尽cpu分配的时间片则进程阻塞
            //能量终点能量未增加 会造成能量盒子中总能量少于初始能量 在因线程调度运行的其它线程
            //运行完该transfer方法时,统计的总能量数据会产生错误 < INITIAL_ENERGY = 1000
            //解决方案1:加互斥锁
            System.out.printf("从%d转移%10.2f单位能量到%d,", from,amount,to);
            energyBoxes[to] += amount;
            System.out.printf("能量总和: %10.2f%n",getTotalEnergies());
            //所有处于Wait Set中的等待的线程被唤醒acquire ObjectMonitor
            lockObj.notifyAll();
        }
    }

    /**
     * 获取能量世界的能量总和
     */
    public double getTotalEnergies(){
        double sum = 0;
        for(double amount:energyBoxes){
            sum += amount;
        }
        return sum;
    }

    /**
     * 返回能量盒子的长度
     */
    public int getBoxAmount(){
        return energyBoxes.length;
    }
}
