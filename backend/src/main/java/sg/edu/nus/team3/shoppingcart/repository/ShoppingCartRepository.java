package sg.edu.nus.team3.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

	// add create, read, update and delete jpql methods

	// add item to cart
	// 
	
	// view items in cart 
	//find all 
	
	
	// update items in cart
	// increase quantity / decrease quantity
	
	// delete items in cart 
	// in built method
	// screpo.deleteById(productId)
	
	@Query ("SELECT c FROM c WHERE c.id = :cartId")
	public ShoppingCart findCartByCartId(@Param("cartId") int cartId);
	
	@Query ("SELECT c FROM c WHERE c.user.id = :userId")
	public ShoppingCart findShoppingCartByUserId(@Param("userId") int userId);


}
