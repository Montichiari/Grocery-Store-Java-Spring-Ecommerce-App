package sg.edu.nus.team3.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

	// find shopping cart by user id
	@Query("SELECT s FROM ShoppingCart s WHERE s.user.id = :id")
	ShoppingCart findShoppingCartByUserId(@Param("userId") int id);
	
	@Query("SELECT s FROM ShoppingCart s WHERE s.id = :cartId")
	ShoppingCart findShoppingCartByCartId(@Param("cartId") int id);

}
