package shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShopControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateProductsAndOrder() {
        // Check, that there are no products in the beginning
        ResponseEntity<Product[]> emptyProductResponse = restTemplate.getForEntity("/shop/products", Product[].class);
        assertThat(emptyProductResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(emptyProductResponse.getBody()).isEmpty();

        // Create a product
        ResponseEntity<Product> createProductResponse = restTemplate.postForEntity("/shop/products", new Product("TV"), Product.class);
        assertThat(createProductResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(Objects.requireNonNull(createProductResponse.getBody()).getName()).isEqualTo("TV");

        // Create another product
        createProductResponse = restTemplate.postForEntity("/shop/products", new Product("PC"), Product.class);
        assertThat(createProductResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(Objects.requireNonNull(createProductResponse.getBody()).getName()).isEqualTo("PC");

        // Check that the products were created
        ResponseEntity<Product[]> getProductsResponse = restTemplate.getForEntity("/shop/products", Product[].class);
        assertThat(getProductsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getProductsResponse.getBody()).hasSize(2);

        // Check, that there are no orders in the beginning
        ResponseEntity<Order[]> emptyOrderResponse = restTemplate.getForEntity("/shop/orders", Order[].class);
        assertThat(emptyOrderResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(emptyOrderResponse.getBody()).isEmpty();

        // Create new Order
        ResponseEntity<Order> createOrderResponse = restTemplate.postForEntity("/shop/orders", List.of(Objects.requireNonNull(getProductsResponse.getBody())[0].getId(), getProductsResponse.getBody()[1].getId()), Order.class);
        assertThat(createOrderResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(Objects.requireNonNull(createOrderResponse.getBody()).getOrderedProducts()).hasSize(2);

        // Try to create Order with unknown ID
        ResponseEntity<Order> unknownProductResponse = restTemplate.postForEntity("/shop/orders", List.of(getProductsResponse.getBody()[0].getId(), "1234"), Order.class);
        assertThat(unknownProductResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        // Check, that only the first Order was created
        ResponseEntity<Order[]> ordersResponse = restTemplate.getForEntity("/shop/orders", Order[].class);
        assertThat(ordersResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ordersResponse.getBody()).hasSize(1);
        assertThat(Objects.requireNonNull(ordersResponse.getBody())[0].getOrderedProducts()).hasSize(2);
    }

}
