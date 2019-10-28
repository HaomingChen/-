package threadcoreknowledge.background;

/**
 * @author Haoming Chen
 * Created on 2019/10/28
 */
public class MultiThreadsError5 {

    int count;

    static class MySource {
        private EventListener listener;

        //将外部EventListener注册进Source类
        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        //利用注册进的listener响应event
        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {
            });
        }).start();
        MultiThreadsError5 multiThreadsError5 = new MultiThreadsError5(mySource);
    }

    public MultiThreadsError5(MySource source) {
        //规定监听器的行为
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

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {

    }
}
