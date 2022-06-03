package shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping(value = "/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return shopService.addProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        try {
            return ResponseEntity.ok(shopService.getProduct(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products")
    public Collection<Product> getProducts() {
        return shopService.listProducts();
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return shopService.listOrders();
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody List<String> products) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(shopService.addOrder(products));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        try {
            return ResponseEntity.ok(shopService.getOrder(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
