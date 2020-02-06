package threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Haoming Chen
 * Created on 2020/2/6
 */
public class ThreadLocalNormalUsage01 {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage01().date(finalI);
                    System.out.println(date);
                }
            }).start();
            Thread.sleep(100);
        }
    }

    public String date(int seconds) {
        Date date = new Date(seconds * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        return dateFormat.format(date);
    }
}
