package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartItemRepository;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;
import java.util.Optional;

// @ authored by @thina 

@Service
@Transactional
public class ShoppingCartServiceImplementation implements ShoppingCartService {

	@Autowired
	ShoppingCartRepository screpo;

	@Autowired
	ShoppingCartItemRepository sc_itemrepo;

	@Override
	@Transactional
	public ShoppingCart findShoppingCartByUserId(int id) {

		ShoppingCart user_shopping_cart = screpo.findShoppingCartByUserId(id);
		return user_shopping_cart;

	}

	@Override
	@Transactional
	public void deleteAllShoppingCartItemsInCart(int user_id_from_session) {
		// find list of shopping cart items by user id
		// find shopping cart by user id
		ShoppingCart user_shopping_cart = findShoppingCartByUserId(user_id_from_session);
		// get list of shopping cart items
		List<ShoppingCartItem> list_of_shopping_cart_items = user_shopping_cart.getItems();

		// delete all items in the cart from the database
		for (int i = 0; i < list_of_shopping_cart_items.size(); i++) {
			// get each shopping cart item id and delete from the database
			ShoppingCartItem shopping_cart_item = list_of_shopping_cart_items.get(i);
			// after getting each shopping cart item, delete each item from the database of
			// shopping cart item
			int shopping_cart_item_id = shopping_cart_item.getId();
			sc_itemrepo.deleteById(shopping_cart_item_id);
		}

		// clear memory of the list after removing from database
		list_of_shopping_cart_items.removeAll(list_of_shopping_cart_items);

	}

	@Override
	public void addToCart(Product product, int qty, HttpSession session) {
		
		ShoppingCart cart = screpo.findCartByCartId((int) session.getAttribute("cartId"));
		
		ShoppingCartItem newCartItem = new ShoppingCartItem(product, qty);
		
		newCartItem.setShoppingCart(cart);
		
		
	}

}
