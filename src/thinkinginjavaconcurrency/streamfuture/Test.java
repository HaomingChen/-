package thinkinginjavaconcurrency.streamfuture;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Haoming Chen
 * Created on 2020/1/14
 */
public class Test {

    public static void main(String[] args) {
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop("Walmart", 10000));
        shops.add(new Shop("bestbuy", 234233));
        List<String> str = shops.stream().map(shop -> shop.getName() + "price is " +
                shop.getPrice()).collect(Collectors.toList());
        System.out.println(str);
    }
}
