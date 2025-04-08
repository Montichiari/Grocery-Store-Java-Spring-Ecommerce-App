package sg.edu.nus.team3.shoppingcart.service;

import java.util.List;

import sg.edu.nus.team3.shoppingcart.model.Product;

public interface ProductService {

  public List<Product> searchProductById(int product_id);

  public double getProductPriceById(int product_id);

  public List<Product> getAllProducts();

  public void editProductById(int product_id, Product product);

  public void createProduct(Product product);

  public void deleteProductById(int product_id);

  public List<Product> searchProductByName(String product_name);

}
