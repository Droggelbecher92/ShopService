package shop;

import java.util.HashMap;
import java.util.List;

public class ProductRepo {

    private HashMap<String,Product> allMyProducts = new HashMap<>();

    public ProductRepo(List<Product> products) {
        for(Product currentProduct : products){
            allMyProducts.put(currentProduct.getId(), currentProduct);
        }
    }

    public List<Product> list(){
        return null;
    }

    public Product get(String whichId){
        return null;
    }
}
