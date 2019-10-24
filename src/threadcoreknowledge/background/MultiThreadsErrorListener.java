package threadcoreknowledge.background;


/**
 * 描述: 观察者模式
 *
 * @author Haoming Chen
 * Created on 2019/10/24
 */
public class MultiThreadsErrorListener {

    int count;

    public MultiThreadsErrorListener(MySource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\n我得到的数字是" + count);
            }
        });
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
        count = 100;
    }

    static class MySource {
        private EventListener listener;

        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {

    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new Event() {
                });
            }
        }).start();
        MultiThreadsErrorListener multiThreadsErrorListener = new MultiThreadsErrorListener(mySource);
    }
}
