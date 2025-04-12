package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.dto.ProductDetailResponse;
import sg.edu.nus.team3.shoppingcart.model.dto.ProductResponse;
import sg.edu.nus.team3.shoppingcart.repository.ProductRepository;
import sg.edu.nus.team3.shoppingcart.service.ProductService;

// @author Jared Chua
@Service
@Transactional
public class ProductServiceImplementation implements ProductService {

  @Autowired
  public ProductRepository productRepo;

  public List<Product> searchProductById(int product_id) {
    List<Product> productInfo = productRepo.findById(product_id).stream().toList();
    return productInfo;
  }

  public List<Product> getAllProducts() {
    List<Product> productList = productRepo.retrieveProductListFromProduct().get();
    return productList;
  }

  public void createProduct(Product product) {
    productRepo.save(product);
  }

  public void editProductById(int product_id, Product product) {
    productRepo.updateProductById(product_id, product.getName(), product.getStock(), product.getUnitPrice(),
        product.getCategory());
  }

  public void deleteProductById(int product_id) {
    productRepo.deleteProductById(product_id);
  }

  public List<Product> searchProductByName(String product_name) {
    List<Product> productInfo = productRepo.findByProductName(product_name).stream().toList();
    return productInfo;
  }
  
  public List<ProductResponse> getAllProductsR() {
      return productRepo.findAll()
              .stream()
              .map(ProductResponse::new)
              .toList();
  }

  public List<ProductResponse> getProductsByCategory(String category) {
      return productRepo.findByCategory(category)
              .stream()
              .map(ProductResponse::new)
              .toList();
  }
  
  public ProductDetailResponse getProductById(int id) {
      Product product = productRepo.findById(id)
              .orElseThrow(() -> new NoSuchElementException("Product not found"));

      return new ProductDetailResponse(product);
  }
}
