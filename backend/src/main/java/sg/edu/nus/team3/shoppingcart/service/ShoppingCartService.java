package sg.edu.nus.team3.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.repository.ProductRepository;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;

public interface ShoppingCartService {
	
	// outline the methods but no need to define method body 
	
	
	public void addShoppingCartItem(int user_id, int scart_item_id); 
	
	public List<ShoppingCartItem> viewShoppingCartItem(int user_id); 
	
	public ShoppingCart createShoppingCart(int user_id); 

	public void clearShoppingCart(int id);


	public void addToCart(int cartId, int productId, int qty);
	
	
	
}
