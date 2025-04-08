package sg.edu.nus.team3.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ShoppingCartServiceImplementation;

@SpringBootTest(classes = ShoppingcartApplication.class)
public class ShoppingCartItemTest {

    @Autowired
    private ShoppingCartServiceImplementation shoppingCartService;

    // * Test updateShoppingCartItemQuantity
    @Test
    @DisplayName("Should update the quantity of a shopping cart item")
    public void updateShoppingCartItemQuantityTest() {
        int cartItemId = 1;  // Assume that cart item with ID=1 exists
        int newQuantity = 5;

        shoppingCartService.updateCartQuantity(cartItemId, newQuantity);

        ShoppingCartItem updatedItem = shoppingCartService.getShoppingCartItemById(cartItemId);
        assertEquals(newQuantity, updatedItem.getQuantity(), "The quantity of the shopping cart item should be updated.");
    }

    // * Test deleteShoppingCartItem
    @Test
    @DisplayName("Should delete a shopping cart item")
    public void deleteShoppingCartItemTest() {
        int cartItemId = 2;  // Assume that cart item with ID=2 exists

        shoppingCartService.deleteCartQuantity(cartItemId);

        ShoppingCartItem deletedItem = shoppingCartService.getShoppingCartItemById(cartItemId);
        assertNull(deletedItem, "The shopping cart item should be deleted.");
    }
}
