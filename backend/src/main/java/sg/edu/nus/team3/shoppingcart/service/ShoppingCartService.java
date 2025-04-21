package sg.edu.nus.team3.shoppingcart.service;

import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;

// @author @ thina 
public interface ShoppingCartService {

	public void deleteAllItemsInCart(int id);

	// @author: Jared Chua
	public void deleteProductFromCart(int id, int productId);

	public void saveShoppingCart(ShoppingCart shoppingCart);

	public ShoppingCart findShoppingCartById(int shoppingCartId);

	public ShoppingCart addItemToCart(int shoppingCartId, int productId, int quantity);

}
