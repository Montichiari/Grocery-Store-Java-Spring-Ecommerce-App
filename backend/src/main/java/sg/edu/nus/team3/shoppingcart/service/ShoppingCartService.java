package sg.edu.nus.team3.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.model.User;


public interface ShoppingCartService {

	// outline the methods but no need to define method body 
	
	
	public void addShoppingCartItem(int user_id, int scart_item_id); 
	
	public List<ShoppingCartItem> viewShoppingCartItem(int user_id); 
	
	public ShoppingCart createShoppingCart(int user_id); 

	public void clearShoppingCart(int id);


	public void addToCart(Product product, int qty, HttpSession session);
	
	
	
}
