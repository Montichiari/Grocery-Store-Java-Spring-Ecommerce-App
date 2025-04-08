package sg.edu.nus.team3.shoppingcart.service;

import java.util.ArrayList;

import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.model.User;


public interface ShoppingCartService {

	// outline the methods but no need to define method body 
	
	
	public void addProductToCart(User id, Product id); 
	
	public void updateCartQuantity(int id, int quantity);
	
	
	public ArrayList<Product> viewCart();

	public void deleteCartQuantity(int id);


	
	
	
	
}
