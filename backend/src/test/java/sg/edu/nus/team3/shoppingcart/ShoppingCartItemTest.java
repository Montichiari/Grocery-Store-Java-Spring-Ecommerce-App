package sg.edu.nus.team3.shoppingcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.Product;

@SpringBootTest
class ShoppingCartItemTest {

    @Autowired
    private ShoppingCartItem shoppingCartItem;

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private Product product;

    @BeforeEach
    void setUp() {
        shoppingCart = new ShoppingCart();
        product = new Product(0, null, 0, 0, null);
        shoppingCartItem = new ShoppingCartItem();
    }

    @Test
    void testAddItemToCartSuccess() {
        shoppingCartItem.addItemToCart(shoppingCart, product, 2);

        assertNotNull(shoppingCartItem.getShoppingCart());
        assertNotNull(shoppingCartItem.getProduct());
        assertTrue(shoppingCartItem.getQuantity() > 0);
    }

    @Test
    void testAddItemToCartWithNullCart() {
        shoppingCartItem.addItemToCart(null, product, 2);

        assertNull(shoppingCartItem.getShoppingCart());
        assertNotNull(shoppingCartItem.getProduct());
    }

    @Test
    void testAddItemToCartWithNullProduct() {
        shoppingCartItem.addItemToCart(shoppingCart, null, 2);

        assertNotNull(shoppingCartItem.getShoppingCart());
        assertNull(shoppingCartItem.getProduct());
    }

    @Test
    void testAddItemToCartWithZeroQuantity() {
        shoppingCartItem.addItemToCart(shoppingCart, product, 0);

        assertEquals(0, shoppingCartItem.getQuantity());
    }

    @Test
    void testAddItemToCartWithNegativeQuantity() {
        shoppingCartItem.addItemToCart(shoppingCart, product, -5);

        assertTrue(shoppingCartItem.getQuantity() < 0);
    }

    @Test
    void testViewItem() {
        shoppingCartItem.addItemToCart(shoppingCart, product, 3);
        ShoppingCartItem viewedItem = shoppingCartItem.viewItem();

        assertNotNull(viewedItem);
        assertEquals(shoppingCartItem, viewedItem);
    }
}
