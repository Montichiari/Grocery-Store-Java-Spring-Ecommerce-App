package sg.edu.nus.team3.shoppingcart.service;

import java.util.List;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import sg.edu.nus.team3.shoppingcart.model.User;


public interface OrderService {
	
	List<Order> findAllOrder();
	public Order saveOrder(Order order);
	Order preCreateOrder(List<OrderItem> orderItems, User user);
	public void updateProductStock(Order order);
	
	double calcTotal(List<OrderItem> orderItems);
	Order getOrderById(int id);

}
