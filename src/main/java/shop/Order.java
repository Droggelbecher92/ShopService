package shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class Order {

    private final String id = UUID.randomUUID().toString();
    private List<Product> orderedProducts;

    public Order(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

}
