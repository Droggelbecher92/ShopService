package shop;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

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
        Product expected = products.get(1);
        //WHEN
        Product actual = testService.getProduct(expected.getId());
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowWithInvalidID(){
        //GIVEN
        ShopService testService = buildShop();
        List<Product> products = testService.listProducts();
        Product expected = products.get(1);
        //WHEN
        //THEN
        assertThrows(NoSuchElementException.class, () -> testService.getProduct("INVALID"));
    }

    @Test
    void shouldAddNewOrder(){
        //GIVEN
        Product p1 = new Product("Wasser");
        Product p2 = new Product("Bier");
        Product p3 = new Product("Messer");
        Product p4 = new Product("Teller");

        ProductRepo pRepo = new ProductRepo(List.of(p1,p2,p3,p4));
        OrderRepo oRepo = new OrderRepo();
        ShopService testService = new ShopService(pRepo,oRepo);
        //WHEN
        testService.addOrder(List.of(p1.getId(),p3.getId(),p4.getId()));
        List<Order> actual = testService.listOrders();
        //THEN
        assertEquals(1,actual.size());
        assertTrue(actual.get(0).getOrderedProducts().containsAll(List.of(p1,p3,p4)));
    }

    @Test
    void shouldThrowWithInvalidId(){
        //GIVEN
        Product p1 = new Product("Wasser");
        Product p2 = new Product("Bier");
        Product p3 = new Product("Messer");
        Product p4 = new Product("Teller");

        ProductRepo pRepo = new ProductRepo(List.of(p1,p2,p3,p4));
        OrderRepo oRepo = new OrderRepo();
        ShopService testService = new ShopService(pRepo,oRepo);
        //WHEN

        //THEN
        assertThrows(NoSuchElementException.class,()-> testService.addOrder(List.of("INVALID")));
    }

    @Test
    void shouldGetOrderById(){
        //GIVEN
        Product p1 = new Product("Wasser");
        Product p2 = new Product("Bier");
        Product p3 = new Product("Messer");
        Product p4 = new Product("Teller");

        ProductRepo pRepo = new ProductRepo(List.of(p1,p2,p3,p4));
        OrderRepo oRepo = new OrderRepo();
        ShopService testService = new ShopService(pRepo,oRepo);
        testService.addOrder(List.of(p1.getId(),p3.getId(),p4.getId()));
        List<Order> list = testService.listOrders();
        //WHEN
        Order actual = testService.getOrder(list.get(0).getId());
        //THEN
        assertEquals(List.of(p1,p3,p4),actual.getOrderedProducts());
    }

    @Test
    void shouldThrowWithInvalidOrderId(){
        //GIVEN
        Product p1 = new Product("Wasser");
        Product p2 = new Product("Bier");
        Product p3 = new Product("Messer");
        Product p4 = new Product("Teller");

        ProductRepo pRepo = new ProductRepo(List.of(p1,p2,p3,p4));
        OrderRepo oRepo = new OrderRepo();
        ShopService testService = new ShopService(pRepo,oRepo);
        testService.addOrder(List.of(p1.getId(),p3.getId(),p4.getId()));
        List<Order> list = testService.listOrders();
        //WHEN
        //THEN
        assertThrows(NoSuchElementException.class,()->testService.getOrder("INVALID"));
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