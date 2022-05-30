package shop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void shouldReturnAllProducts(){
        //GIVEN
        Product p1 = new Product("Wasser");
        Product p2 = new Product("Bier");
        Product p3 = new Product("Messer");
        Product p4 = new Product("Teller");

        ProductRepo pRepo = new ProductRepo(List.of(p1,p2,p3,p4));
        OrderRepo oRepo = new OrderRepo();

        ShopService testService = new ShopService(pRepo,oRepo);
        //WHEN
        List<Product> actual = testService.listProducts();
        //THEN
        assertEquals(4,actual.size());
        assertTrue(actual.containsAll(List.of(p1,p2,p3,p4)));
    }


    @Test
    void shouldGetProductWithValidID(){
        //GIVEN
        ShopService testService = buildShop();
        List<Product> products = testService.listProducts();
        //WHEN

        //THEN

    }


    //Hilfsmethoden
    private ShopService buildShop(){
        Product p1 = new Product("Wasser");
        Product p2 = new Product("Bier");
        Product p3 = new Product("Messer");
        Product p4 = new Product("Teller");

        ProductRepo pRepo = new ProductRepo(List.of(p1,p2,p3,p4));
        OrderRepo oRepo = new OrderRepo();

        return new ShopService(pRepo,oRepo);

    }

}