package shop;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ProductRepo {

    private HashMap<String,Product> allMyProducts = new HashMap<>();

    public ProductRepo(List<Product> products) {
        for(Product currentProduct : products){
            allMyProducts.put(currentProduct.getId(), currentProduct);
        }
    }

    public List<Product> list(){
        return allMyProducts.values().stream().toList();
    }

    public Optional<Product> get(String whichId){
        return Optional.ofNullable(allMyProducts.get(whichId));
    }
}
