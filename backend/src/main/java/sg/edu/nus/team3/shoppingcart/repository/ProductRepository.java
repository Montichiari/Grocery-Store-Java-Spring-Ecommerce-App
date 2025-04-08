package sg.edu.nus.team3.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.nus.team3.shoppingcart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p")
    public List<Product> getProductList();

}
