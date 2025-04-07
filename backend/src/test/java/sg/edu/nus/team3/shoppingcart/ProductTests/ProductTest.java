
package sg.edu.nus.team3.shoppingcart.ProductTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.team3.shoppingcart.ShoppingcartApplication;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ProductServiceImplementation;

@SpringBootTest(classes = ShoppingcartApplication.class)
public class ProductTest {
  @Autowired
  private ProductServiceImplementation productServiceImpl;

  // * Tests for getAllProducts
  @Test
  @DisplayName("Should return a list of products")
  public void getAllProductsTest() {
    List<Product> productList = productServiceImpl.getAllProducts();
    int entryCount = productList.size();
    // Test for multiple entry retrievals
    assertTrue(entryCount > 0, "There should be at least 1 product returned.");
  }

  // * Tests for searchProductById
  @Test
  @DisplayName("Should return a list of 1 product")
  public void getOneProductById() {
    List<Product> getProduct = productServiceImpl.searchProductById(1);
    int entryCount = getProduct.size();

    // Test for correct search index
    assertTrue(getProduct.get(0).getName() == "Laptop", "The product 'laptop' should've been returned.");
    // Test for singular entry retrieval
    assertTrue(entryCount == 1, "Only 1 entry should've been returned.");
  }

  // ? Tests for searchProductById (Not necessary since we can searchProductById)

  // * Tests for createProduct
  @Test
  @DisplayName("Should create a new product, with auto generated ID")
  public void createNewProduct() {
    Product createdProduct = new Product();
  }

  // * Tests for editProductById

  // * Tests for deleteProductById
}
