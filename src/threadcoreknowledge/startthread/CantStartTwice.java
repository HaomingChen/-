package threadcoreknowledge.startthread;

/**
 * @author Haoming Chen
 * Created on 2019/10/15
 */
public class CantStartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread Started");
            }
        });
        thread.start();
        thread.start();
    }

}
