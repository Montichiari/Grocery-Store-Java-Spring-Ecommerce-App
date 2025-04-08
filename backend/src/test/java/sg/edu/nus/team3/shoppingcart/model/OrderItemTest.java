package sg.edu.nus.team3.shoppingcart.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ProductServiceImplementation;

import java.util.List;

@SpringBootTest
public class OrderItemTest {
    @Autowired
    private ProductServiceImplementation productServiceImpl;
    @Test
    @DisplayName("make the OrderItem")
    public void testOrderItem() {
        List<Product> list= productServiceImpl.editProductById();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(4);
        orderItem.setQuantity(10);
        orderItem.setUnitPrice(60.00);
    }
}
