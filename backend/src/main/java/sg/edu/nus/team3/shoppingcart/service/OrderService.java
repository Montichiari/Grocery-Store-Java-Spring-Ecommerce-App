package sg.edu.nus.team3.shoppingcart.service;

import java.util.List;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import sg.edu.nus.team3.shoppingcart.model.User;


public interface OrderService {
	
	List<Order> findAllOrder();
	Order preCreateOrder(List<OrderItem> orderItems, User user);
	Order createOrder(Order order, User user);
	
	double calcTotal(List<OrderItem> orderItems);
	Order getOrderById(int id);
	//User getUserByOrderId(int id);

}
