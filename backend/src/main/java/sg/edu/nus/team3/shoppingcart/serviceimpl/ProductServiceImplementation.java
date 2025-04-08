package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.repository.ProductRepository;
import sg.edu.nus.team3.shoppingcart.service.ProductService;

// @author Jared Chua
@Service
@Transactional
public class ProductServiceImplementation implements ProductService {

  @Autowired
  public ProductRepository productRepo;

  public List<Product> searchProductById(int product_id) {
    // TODO: Finish implementation of searchProductById
    return new ArrayList<Product>();
  }

  public double getProductPriceById(int product_id) {
    // TODO: Finish implementation of getProductPriceById
    return 0.0;
  }

  @Transactional
  public List<Product> getAllProducts() {
    // TODO: Finish implementation of getAllProducts()
    List<Product> productList = productRepo.getProductList();
    // System.out.println(productList.size());
    return productList;
  }

  public void createProduct(Product product) {
    // TODO: Finish implementation of createProduct()
  }

  public void editProductById(int product_id, Product product) {
    // TODO: Finish implementation of editProductById()

  }

  public void deleteProductById(int product_id) {
    // TODO: Finish implementation of deleteProductById()
  }

  public List<Product> searchProductByName(String product_name) {
    // TODO: Finish implementation of searchProductByName
    return new ArrayList<Product>();
  }
}
