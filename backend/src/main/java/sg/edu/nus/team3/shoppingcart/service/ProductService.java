package sg.edu.nus.team3.shoppingcart.service;

import java.util.List;

import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.dto.ProductDetailResponse;
import sg.edu.nus.team3.shoppingcart.model.dto.ProductResponse;

// @author Jared Chua
public interface ProductService {

  public List<Product> searchProductById(int product_id);

  public List<Product> getAllProducts();

  public void editProductById(int product_id, Product product);

  public void createProduct(Product product);

  public void deleteProductById(int product_id);

  public List<Product> searchProductByName(String product_name);
  
  public List<ProductResponse> getAllProductsR();
  
  public List<ProductResponse> getProductsByCategory(String category);
  
  public ProductDetailResponse getProductById(int id);
  
  public Product findById(int id);

}
