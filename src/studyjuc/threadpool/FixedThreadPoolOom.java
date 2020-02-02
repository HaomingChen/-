package studyjuc.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 58212
 * @date 2020-02-03 1:10
 */
public class FixedThreadPoolOom {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            //-Xmx8m -Xms8m
            service.execute(new FixedThreadPoolTest.Task2());
        }
    }

}
