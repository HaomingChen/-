package singleton;

/**
 * 懒汉式
 *
 * @author 58212
 * @date 2019-11-04 22:34
 */
public class SingletonHu {

    static final SingletonHu singletonHu = new SingletonHu();

    private SingletonHu() {
    }

    public SingletonHu getSingleton() {
        return singletonHu;
    }
}
