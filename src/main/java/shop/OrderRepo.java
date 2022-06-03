package shop;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepo {

    private HashMap<String,Order> allMyOrders = new HashMap<>();

    public List<Order> list(){
        return allMyOrders.values().stream().toList();
    }

    public Optional<Order> get(String whichId){
        return Optional.ofNullable(allMyOrders.get(whichId));
    }

    public Order add(Order oderToAdd){
        allMyOrders.put(oderToAdd.getId(),oderToAdd);
        return oderToAdd;
    }

}
