package shop;

import java.util.ArrayList;
import java.util.List;

public class ShopService {

    private final ProductRepo myProducts;
    private final OrderRepo myOrders;

    public ShopService(ProductRepo myProducts, OrderRepo myOrders) {
        this.myProducts = myProducts;
        this.myOrders = myOrders;
    }


    public Product getProduct(String id){
        return myProducts.get(id).orElseThrow();
    }

    public List<Product> listProducts(){
        return myProducts.list();
    }

    public void addOrder(List<String> productsToOrder){
        List<Product> orderedProducts = new ArrayList<>();
        for (String id : productsToOrder){
            orderedProducts.add(getProduct(id));
        }
        myOrders.add(new Order(orderedProducts));
    }

    public Order getOrder(String id){
        return myOrders.get(id).orElseThrow();
    }

    public List<Order> listOrders(){
        return myOrders.list();
    }

}
