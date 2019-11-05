package singleton;

/**
 * @author 58212
 * @date 2019-11-05 22:19
 */
public class SingletonLazy1 {
    private SingletonLazy1 singletonLazy1;

    private SingletonLazy1() {

    }

    public SingletonLazy1 createInstance() {
        if (singletonLazy1 == null) {
            return new SingletonLazy1();
        } else {
            return singletonLazy1;
        }
    }
}
