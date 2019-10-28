package threadcoreknowledge.background;

import java.util.HashMap;
import java.util.Map;

/**
 * 构造函数中新建线程
 *
 * @author Haoming Chen
 * Created on 2019/10/28
 */
public class MultiThreadsError6 {

    private Map<String, String> states;

    public MultiThreadsError6() {
        new Thread(() -> {
            states = new HashMap<>();
            states.put("1", "周一");
            states.put("2", "周二");
            states.put("3", "周三");
            states.put("4", "周四");
        }).start();
    }

    public Map<String, String> getStates() {
        return states;
    }

    public Map<String, String> getSafeStates() {
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MultiThreadsError6 multiThreadsError6 = new MultiThreadsError6();
        Map<String, String> states = multiThreadsError6.getSafeStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));
    }
}
