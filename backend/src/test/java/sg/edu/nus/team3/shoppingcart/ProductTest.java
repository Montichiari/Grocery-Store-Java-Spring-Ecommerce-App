package sg.edu.nus.team3.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
  @DisplayName("Should return a list of 1 product through searching of the product id")
  public void getOneProductById() {
    List<Product> getProduct = productServiceImpl.searchProductById(1);
    int entryCount = getProduct.size();

    // Test for correct search index
    assertTrue(getProduct.get(0).getName().equalsIgnoreCase("Laptop"), "The product 'laptop' should've been returned.");
    // Test for singular entry retrieval
    assertTrue(entryCount == 1, "Only 1 entry should've been returned.");
  }

  // * Tests for searchProductByName
  @Test
  @DisplayName("Should return a list of 1 product through searching of the product name")
  public void getOneProductByName() {
    List<Product> getProductByName = productServiceImpl.searchProductByName("Laptop");
    int entryCount = getProductByName.size();

    // Test for correct search index
    assertTrue(getProductByName.get(0).getName().equalsIgnoreCase("Laptop"),
        "The product 'laptop' should've been returned.");
    // Test for singular entry retrieval
    assertTrue(entryCount == 1, "Only 1 entry should've been returned.");
  }

  // ? Tests for getProductPriceById (Not necessary since we can get the same data
  // from searchProductById or searchProductByName)

  // * Tests for createProduct
  @Test
  @DisplayName("Should create a new product, with auto generated ID")
  public void createNewProduct() {
    Product createdProduct = new Product(99999, "TestProduct", 999.99, 888, "Vegetables");
    productServiceImpl.createProduct(createdProduct);
    List<Product> getProductByName = productServiceImpl.searchProductByName("TestProduct");

    // Test for newly created product
    assertTrue(getProductByName.get(0).getName().equalsIgnoreCase("TestProduct"),
        "The 'TestProduct' should've been returned.");
  }

  // * Tests for editProductById
  @Test
  @DisplayName("Should edit an existing product based on their ID")
  public void updateProductById() {
    int productId = 99999;
    Product productToBeEdited = new Product(productId, "TestEditProduct", 2999.99, 2999, "Test");
    productServiceImpl.editProductById(productId, productToBeEdited);

    List<Product> getEditedProduct = productServiceImpl.searchProductById(productId);
    assertTrue(getEditedProduct.equals(productToBeEdited), "The product was not updated with all the provided data.");
  }

  // * Tests for deleteProductById
  @Test
  @DisplayName("Should delete an existing product based on their ID")
  public void deleteProduct() {
    int productId = 99999;
    productServiceImpl.deleteProductById(productId);

    List<Product> getDeletedProduct = productServiceImpl.searchProductById(productId);
    assertTrue(getDeletedProduct.size() == 0, "The product was not deleted successfully.");
  }
}
