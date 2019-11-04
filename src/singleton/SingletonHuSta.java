package singleton;

/**
 * @author 58212
 * @date 2019-11-04 22:38
 */
public class SingletonHuSta {
    private static SingletonHuSta singletonHuSta;

    static {
        singletonHuSta = new SingletonHuSta();
    }

    private SingletonHuSta(){
    }

    public static SingletonHuSta getSingletonHuSta() {
        return singletonHuSta;
    }
}
