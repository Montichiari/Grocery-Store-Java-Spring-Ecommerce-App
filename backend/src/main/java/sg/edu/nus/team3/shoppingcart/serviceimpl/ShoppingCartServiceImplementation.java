package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;

@Service
@Transactional
public class ShoppingCartServiceImplementation implements ShoppingCartService {

	@Autowired
	ShoppingCartRepository screpo;

	@Override
	@Transactional
	public void addShoppingCartItem(int user_id, int scart_item_id) {

	}

	@Override
	@Transactional
	public List<ShoppingCartItem> viewShoppingCartItem(int user_id) {
		// TODO Auto-generated method stub
		return null;
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
