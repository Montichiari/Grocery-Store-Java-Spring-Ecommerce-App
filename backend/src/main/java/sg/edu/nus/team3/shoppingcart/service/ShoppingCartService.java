package sg.edu.nus.team3.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.model.User;

public interface ShoppingCartService {

	// outline the methods but no need to define method body

	public List<ShoppingCartItem> addShoppingCartItem(int user_id, int scart_item_id);

	public ShoppingCart createShoppingCart(int user_id);

	public void clearShoppingCart(int id);

	public ShoppingCart findShoppingCartByUserEmail();

}
