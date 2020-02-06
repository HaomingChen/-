package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用线程池创建线程打印时间
 * 静态dateFormat
 *
 * @author Haoming Chen
 * Created on 2020/2/6
 */
public class ThreadLocalNormalUsage03 {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int time = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(new ThreadLocalNormalUsage03().date(time));
                }
            });
        }

    }

    public String date(int seconds) {
        Date date = new Date(seconds * 1000);
        return dateFormat.format(date);
    }

}
