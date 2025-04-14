package sg.edu.nus.team3.shoppingcart.service;

import sg.edu.nus.team3.shoppingcart.model.Order;


public interface OrderService {
	
	public Order checkoutCart(int userId, int cartId, String paymentMethod);
	Order getOrderById(int id);
	
}
