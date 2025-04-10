package sg.edu.nus.team3.shoppingcart.serviceimpl;

import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartItemRepository;

public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

	@Autowired
	private ShoppingCartItemRepository shoppingCartItemRepository;

	@Override
	public void updateShoppingCartItemQuantity() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteShoppingCartItem() {
		// TODO Auto-generated method stub
	}

	// authored by @thina
	@Override
	@Transactional
	public ShoppingCartItem createShoppingCartItem(ShoppingCart user_shopping_cart, Product product_item,
			int quantity) {
		// create new shopping cart item
		ShoppingCartItem converted_product_to_shopping_cart_item = new ShoppingCartItem(user_shopping_cart,
				product_item,
				quantity);
		// save this new shopping cart item to the database
		shoppingCartItemRepository.save(converted_product_to_shopping_cart_item);
		return converted_product_to_shopping_cart_item;
		// then put this shopping cart item into the shopping cart

	}

}
