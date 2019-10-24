package threadcoreknowledge.background;

/**
 * 初始化未完毕，就this赋值
 *
 * @author Haoming Chen
 * Created on 2019/10/24
 */
public class MultiThreadsError4 {
    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        Thread.sleep(10);
        if (point != null) {
            System.out.println(point);
        }
    }
}

class Point {

    private final int x, y;

    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        MultiThreadsError4.point = this;
        Thread.sleep(10);
        this.y = y;
    }

    @Override
    public String toString() {
        return "x = " + x + " y = " + y;
    }
}

class PointMaker extends Thread {
    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
