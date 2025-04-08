package sg.edu.nus.team3.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ShoppingCartServiceImplementation;

@SpringBootTest(classes = ShoppingcartApplication.class)
public class ShoppingCartItemTest {
  
  @Autowired
  private ShoppingCartServiceImplementation shoppingCartServiceImpl;

  private ShoppingCart shoppingCart;
  private Product product;

  @BeforeEach
  void setUp() {
    shoppingCart = new ShoppingCart(); // 初始化购物车
    product = new Product(1, "Test Product", 100.0, 10, "Category"); // 创建一个测试商品
  }

  // * Tests for addItemToCart
  @Test
  @DisplayName("Should add an item to the shopping cart")
  public void addItemToCartTest() {
    ShoppingCartItem item = shoppingCartServiceImpl.addItemToCart(shoppingCart, product, 2);

    // 确保返回的购物车项不为空
    assertNotNull(item, "ShoppingCartItem should not be null after adding.");
    // 确保购物车项包含正确的商品和数量
    assertTrue(item.getProduct().equals(product), "The added product does not match.");
    assertTrue(item.getQuantity() == 2, "The quantity of the added product is incorrect.");
  }

  @Test
  @DisplayName("Should not add an item when cart is null")
  public void addItemToCartWithNullCartTest() {
    ShoppingCartItem item = shoppingCartServiceImpl.addItemToCart(null, product, 2);

    assertTrue(item == null, "Item should not be added when the cart is null.");
  }

  @Test
  @DisplayName("Should not add an item when product is null")
  public void addItemToCartWithNullProductTest() {
    ShoppingCartItem item = shoppingCartServiceImpl.addItemToCart(shoppingCart, null, 2);

    assertTrue(item == null, "Item should not be added when the product is null.");
  }

  @Test
  @DisplayName("Should not add an item when quantity is zero")
  public void addItemToCartWithZeroQuantityTest() {
    ShoppingCartItem item = shoppingCartServiceImpl.addItemToCart(shoppingCart, product, 0);

    assertNotNull(item, "ShoppingCartItem should be created even if quantity is 0.");
    assertTrue(item.getQuantity() == 0, "The quantity should be 0.");
  }

  @Test
  @DisplayName("Should allow adding an item with negative quantity")
  public void addItemToCartWithNegativeQuantityTest() {
    ShoppingCartItem item = shoppingCartServiceImpl.addItemToCart(shoppingCart, product, -3);

    assertNotNull(item, "ShoppingCartItem should be created even if quantity is negative.");
    assertTrue(item.getQuantity() == -3, "The quantity should be negative.");
  }

  // * Tests for viewItem
  @Test
  @DisplayName("Should return the correct item when viewed")
  public void viewItemTest() {
    ShoppingCartItem addedItem = shoppingCartServiceImpl.addItemToCart(shoppingCart, product, 3);
    ShoppingCartItem viewedItem = shoppingCartServiceImpl.viewItem(addedItem);

    assertNotNull(viewedItem, "Viewed ShoppingCartItem should not be null.");
    assertTrue(viewedItem.equals(addedItem), "The viewed item does not match the expected one.");
  }
}
