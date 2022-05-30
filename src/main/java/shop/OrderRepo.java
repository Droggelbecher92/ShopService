package shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderRepo {

    private Map<String,Order> allMyOrders = new HashMap<>();

    public List<Order> list(){
        return allMyOrders.values().stream().toList();
    }

    public Optional<Order> get(String whichId){
        return Optional.ofNullable(allMyOrders.get(whichId));
    }

    public void add(Order oderToAdd){
        allMyOrders.put(oderToAdd.getId(),oderToAdd);
    }

}
