package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartServiceImplementation implements ShoppingCartService {

	@Autowired
	ShoppingCartRepository screpo;

	@Override
	@Transactional
	public List<ShoppingCartItem> addShoppingCartItem(int user_id, int scart_item_id) {
		return null;

	}

	@Override
	@Transactional
	public ShoppingCart findShoppingCartByUserEmail() {

		ShoppingCart user_shopping_cart = screpo.findShoppingCartByUserEmail("email");
		return user_shopping_cart;

	}

	@Override
	public ShoppingCart createShoppingCart(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearShoppingCart(int id) {
		// TODO Auto-generated method stub

	}

}
