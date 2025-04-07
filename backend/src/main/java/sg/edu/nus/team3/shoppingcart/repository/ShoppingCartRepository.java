package sg.edu.nus.team3.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {

	// add create, read, update and delete jpql methods
}
