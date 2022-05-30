package shop;

import java.util.List;

public class ShopService {

    private final ProductRepo myProducts;
    private final OrderRepo myOrders;

    public ShopService(ProductRepo myProducts, OrderRepo myOrders) {
        this.myProducts = myProducts;
        this.myOrders = myOrders;
    }


    public Product getProduct(String id){
        return null;
    }

    public List<Product> listProducts(){
        return myProducts.list();
    }

    public void addOrder(Order orderToAdd){

    }

    public Order getOrder(String id){
        return null;
    }

    public List<Order> listOrders(){
        return null;
    }

}
