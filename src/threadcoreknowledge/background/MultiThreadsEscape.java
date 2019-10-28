package threadcoreknowledge.background;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Haoming Chen
 * Created on 2019/10/24
 */
public class MultiThreadsEscape {

    private Map<String, String> states;

    public MultiThreadsEscape() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }

    public Map<String, String> getStates() {
        return states;
    }

    public Map<String, String> getSafeStates() {
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MultiThreadsEscape multiThreadsEscape = new MultiThreadsEscape();
        Map<String, String> states = multiThreadsEscape.getSafeStates();
        System.out.println(multiThreadsEscape.getSafeStates().get("1"));
        states.remove("1");
        System.out.println(multiThreadsEscape.getSafeStates().get("1"));
    }

}
