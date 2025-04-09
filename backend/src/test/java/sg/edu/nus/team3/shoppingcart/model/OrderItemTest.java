package sg.edu.nus.team3.shoppingcart.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ProductServiceImplementation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OrderItemTest {
    @Autowired
    private ProductServiceImplementation productServiceImpl;
    @Test
    @DisplayName("Make the OrderItem")
    public void testOrderItem() {
        // Arrange
        List<Product> productList = productServiceImpl.getAllProducts();
        OrderItem orderItem = new OrderItem();

        // Act
        orderItem.setId(4);
        orderItem.setUnitPrice(60.00);
        orderItem.setUnitPrice(10);

        // Assert
        assertTrue(orderItem.getId() == 4, "OrderItem ID should be 4");
        assertTrue(orderItem.getQuantity() == 10, "Quantity should be 10");
        assertTrue(orderItem.getUnitPrice() == 60.00, "Unit price should be 60.00");
        assertTrue(productList != null, "Product list should not be null");
    }
}
