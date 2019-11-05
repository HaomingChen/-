package singleton;

/**
 * 单例模式实现单例
 *
 * @author 58212
 * @date 2019-11-06 1:13
 */
public enum SingletonInPractice {
    INSTANCE;

    public void printSomething() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        SingletonInPractice.INSTANCE.printSomething();
    }
}
