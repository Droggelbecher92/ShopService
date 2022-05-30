package shop;

import java.util.List;
import java.util.UUID;

public class Order {

    private final String id;
    private final List<Product> orderedProducts;

    public Order(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
        id= UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }
}
