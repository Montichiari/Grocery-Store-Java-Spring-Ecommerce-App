package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;
import java.util.Optional;

// @ authored by @thina 

@Service
@Transactional
public class ShoppingCartServiceImplementation implements ShoppingCartService {

	@Autowired
	ShoppingCartRepository screpo;

	// @Override
	// @Transactional
	// public List<ShoppingCartItem> addShoppingCartItem(int user_id, int
	// scart_item_id) {
	// return null;

	// }

	@Override
	@Transactional
	public ShoppingCart findShoppingCartByUserId(int id) {

		ShoppingCart user_shopping_cart = screpo.findShoppingCartByUserId(id);
		return user_shopping_cart;

	}

	// @Override
	// public void clearShoppingCart(String email) {
	// // find shopping cart by user email

	// }

}
