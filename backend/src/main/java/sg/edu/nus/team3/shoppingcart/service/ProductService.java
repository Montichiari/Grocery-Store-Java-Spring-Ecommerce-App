package sg.edu.nus.team3.shoppingcart.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.repository.ProductRepository;

// @author Jared Chua
public interface ProductService {
  public List<Product> searchProductById(int product_id);

  public List<Product> getAllProducts();

  public void editProductById(int product_id, Product product);

  public void createProduct(Product product);

  public void deleteProductById(int product_id);

  public List<Product> searchProductByName(String product_name);

  public List<Product> getProductsByPriceRange(double min, double max);
  }
