package singleton;

/**
 * @author 58212
 * @date 2019-11-05 22:19
 */
public class SingletonLazy2 {
    private SingletonLazy2 singletonLazy2;

    private SingletonLazy2() {

    }

    public synchronized SingletonLazy2 createInstance() {
        if (singletonLazy2 == null) {
            return new SingletonLazy2();
        } else {
            return singletonLazy2;
        }
    }
}

