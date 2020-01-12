package interviewqs;

/**
 * 双重检查锁模式 -> 首先检查该对象的实例是否存在 -> 获取该对象的类锁 ->
 *
 * @author 58212
 * @date 2020-01-12 18:25
 */
public class DoubleCheckedSingleton {
    //加上volatile关键字避免指令重排序
    private volatile static DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton(){

    }

    public static DoubleCheckedSingleton getInstance(){
        //检查该对象是否已被实例化
        if(instance == null){
            //线程不安全, 其它线程可以在此处实例化对象
            synchronized (DoubleCheckedSingleton.class){
                if(instance == null) {
                    //指令错误的重排序为 注意synchronize关键字无法防止指令重排序
                    //关键 -> 类的实例化和赋值并不是原子性操作
                    //1. construct empty resource
                    //2. assign to instance
                    //3. call constructor
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
