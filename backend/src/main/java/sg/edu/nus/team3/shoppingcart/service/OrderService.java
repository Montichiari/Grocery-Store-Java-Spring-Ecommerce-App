package sg.edu.nus.team3.shoppingcart.service;

import java.util.List;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;


public interface OrderService {
	
	List<Order> findAllOrder();

	//Order createOrder(OrderItem orderItems);

}
