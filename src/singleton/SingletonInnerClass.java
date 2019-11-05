package singleton;

/**
 * @author 58212
 * @date 2019-11-06 0:47
 */
public class SingletonInnerClass {

    private SingletonInnerClass() {

    }

    //当调用geiSingleton这个静态内部类后 -> singletonInnerClass才会被初始化
    private static class getSingleton {
        static SingletonInnerClass singletonInnerClass = new SingletonInnerClass();
    }

    public static SingletonInnerClass getSingletonOfThisClass() {
        return getSingleton.singletonInnerClass;
    }
}
