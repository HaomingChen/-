package singleton;

/**
 * @author 58212
 * @date 2019-11-05 22:19
 */
public class SingletonLazy3 {
    private static SingletonLazy3 singletonLazy3;

    private SingletonLazy3() {

    }

    public static SingletonLazy3 createInstance() {
        //线程不安全
        Thread.currentThread().getState();
        //当singletonLazy3已经被初始化的情况下, 不需要再获得锁来判断该对象是否存在，提高了性能
        if (singletonLazy3 == null) {
            synchronized (SingletonLazy3.class) {
                if (singletonLazy3 == null) {
                    return new SingletonLazy3();
                } else {
                    return singletonLazy3;
                }
            }
        } else {
            return singletonLazy3;
        }
    }

}

