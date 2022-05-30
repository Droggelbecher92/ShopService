package shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRepo {

    private Map<String,Product> allMyProducts = new HashMap<>();

    public ProductRepo(List<Product> products) {
        allMyProducts = products.stream().collect(Collectors.toMap(p -> p.getId(), p -> p));
    }

    public List<Product> list(){
        return allMyProducts.values().stream().toList();
    }

    public Optional<Product> get(String whichId){
        return Optional.ofNullable(allMyProducts.get(whichId));
    }
}
