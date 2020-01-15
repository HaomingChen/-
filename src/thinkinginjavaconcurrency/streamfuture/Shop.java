package thinkinginjavaconcurrency.streamfuture;

/**
 * @author Haoming Chen
 * Created on 2020/1/14
 */
public class Shop {

    public Shop(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    private String name;

    private Integer price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
