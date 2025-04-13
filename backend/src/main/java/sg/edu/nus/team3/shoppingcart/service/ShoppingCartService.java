package sg.edu.nus.team3.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;
import jakarta.transaction.Transactional;

import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.model.User;

public interface ShoppingCartService {

	public void deleteAllItemsInCart(int id);

	public ShoppingCart findShoppingCartByUserId(int id);

	public void saveShoppingCart(ShoppingCart shoppingCart);

}
