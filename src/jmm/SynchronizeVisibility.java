package jmm;

import com.imooc.synchronize.SynchronizeYesOrNo6;

/**
 * @author 58212
 * @date 2019-11-04 21:32
 */
public class SynchronizeVisibility {

    int a = 1;
    int b = 2;
    int c = 3;
    int d = 4;

    public void change() {
        a = 7;
        b = 8;
        c = 9;
        synchronized (this) {
            d = 10;
        }
    }

    public static void main(String[] args) {
        SynchronizeVisibility synVis = new SynchronizeVisibility();
        while (true) {
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synVis.change();
            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synVis.print();
            }).start();
        }
    }

    public void print() {
        synchronized (this) {
            int aa = a;
        }
        int bb = b;
        int cc = c;
        int dd = d;
        System.out.println(" cc: " + cc + " dd: " + dd);
    }
}
