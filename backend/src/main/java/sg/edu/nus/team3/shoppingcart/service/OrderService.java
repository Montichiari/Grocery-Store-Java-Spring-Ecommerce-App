package sg.edu.nus.team3.shoppingcart.service;

import java.util.List;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import sg.edu.nus.team3.shoppingcart.model.User;


public interface OrderService {
	
	public Order checkoutCart(int userId, int cartId);
	
	List<Order> findAllOrder();
	public Order saveOrder(Order order);
	Order getOrderById(int id);
	
	public void updateProductStock(Order order);
	
	//Order preCreateOrder(List<OrderItem> orderItems, User user);
	//double calcTotal(List<OrderItem> orderItems);
	

}
