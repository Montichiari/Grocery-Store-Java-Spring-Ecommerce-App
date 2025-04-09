package sg.edu.nus.team3.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

	// find shopping cart by user id
	@Query("SELECT s FROM ShoppingCart s WHERE s.user.email LIKE CONCAT('%', :email, '%')")
	ShoppingCart findShoppingCartByUserEmail(String email);

}
