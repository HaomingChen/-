package threadcoreknowledge.createthread;

/**
 * 用Runnable方式实现线程
 *
 * @author Haoming Chen
 * Created on 2019/10/14
 */
public class RunnableStyle implements Runnable {

    public static void main(String[] args) {
        RunnableStyle runnableStyle = new RunnableStyle();
        //使用Runnable实现线程执行新任务时, 不需要创建新线程
        //传入继承Runnable的类, 在Thread类中this.target = runnable
        //Runnable target;
        //在run()方法中运行 if(target != null ) - > target.run();
        Thread thread = new Thread(runnableStyle);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用Runnable方法实现线程");
    }

}
