package sg.edu.nus.team3.shoppingcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.Product;


class ShoppingCartItemTest {

    private ShoppingCartItem shoppingCartItem;
    private ShoppingCart shoppingCart;
    private Product product;

    @BeforeEach
    void setUp() {
        shoppingCartItem = new ShoppingCartItem();
        shoppingCart = new ShoppingCart();
        product = new Product();
    }

    @Test
    void testAddItemToCartSuccess() {
        shoppingCartItem.addItemToCart(shoppingCart, product, 2);

        assertEquals(shoppingCart, shoppingCartItem.getShoppingCart());
        assertEquals(product, shoppingCartItem.getProduct());
        assertEquals(2, shoppingCartItem.getQuantity());
    }

    @Test
    void testAddItemToCartWithNullCart() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingCartItem.addItemToCart(null, product, 2);
        });

        assertEquals("购物车和商品不能为null", exception.getMessage());
    }

    @Test
    void testAddItemToCartWithNullProduct() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingCartItem.addItemToCart(shoppingCart, null, 2);
        });

        assertEquals("购物车和商品不能为null", exception.getMessage());
    }

    @Test
    void testAddItemToCartWithZeroQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingCartItem.addItemToCart(shoppingCart, product, 0);
        });

        assertEquals("数量必须大于0", exception.getMessage());
    }

    @Test
    void testAddItemToCartWithNegativeQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingCartItem.addItemToCart(shoppingCart, product, -5);
        });

        assertEquals("数量必须大于0", exception.getMessage());
    }

    @Test
    void testViewItem() {
        shoppingCartItem.addItemToCart(shoppingCart, product, 3);
        ShoppingCartItem viewedItem = shoppingCartItem.viewItem();

        assertNotNull(viewedItem);
        assertEquals(shoppingCartItem, viewedItem);
    }
}
