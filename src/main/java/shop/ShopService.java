package shop;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    public Order addOrder(List<String> productsToOrder){
        List<Product> orderedProducts = new ArrayList<>();
        for (String id : productsToOrder){
            orderedProducts.add(getProduct(id));
        }
        return myOrders.add(new Order(orderedProducts));
    }

    public Order getOrder(String id){
        return myOrders.get(id).orElseThrow();
    }

    public List<Order> listOrders(){
        return myOrders.list();
    }

    public Product addProduct(Product product) {
        return myProducts.add(product);
    }
}
