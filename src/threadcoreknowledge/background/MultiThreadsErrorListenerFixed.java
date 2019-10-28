package threadcoreknowledge.background;

/**
 * @author Haoming Chen
 * Created on 2019/10/28
 */
public class MultiThreadsErrorListenerFixed {

    int count;
    EventListener listener;

    private MultiThreadsErrorListenerFixed() {
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\n我得到的数字是" + count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static MultiThreadsErrorListenerFixed multiThreadsErrorListenerFac(MySource source) {
        //先实例化完成MultiThreadsErrorListenerFixed类
        MultiThreadsErrorListenerFixed multiThreadsErrorListenerFixed = new MultiThreadsErrorListenerFixed();
        //再注册监听器
        source.registerListener(multiThreadsErrorListenerFixed.listener);
        return multiThreadsErrorListenerFixed;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {
            });
        }).start();
        //不会输出错误结果, 因线程到来时, 若MultiThreadsErrorListenerFixed类未初始化完成,便不会注册
        MultiThreadsErrorListenerFixed multiThreadsErrorListenerFixed = MultiThreadsErrorListenerFixed.
                multiThreadsErrorListenerFac(mySource);
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
}