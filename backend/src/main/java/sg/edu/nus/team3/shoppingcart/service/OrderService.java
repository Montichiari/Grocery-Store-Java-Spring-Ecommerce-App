package sg.edu.nus.team3.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import sg.edu.nus.team3.shoppingcart.model.dto.OrderSummaryResponse;
import sg.edu.nus.team3.shoppingcart.projections.OrderProjection;

public interface OrderService {
	public Order createOrder(List<OrderItem> orderItems);

	public Optional<Order> getOrderById(int order_id);

	public List<Order> getAllOrders();

	public List<OrderProjection> getWeeklyOrders();
	
	public Order checkout(int userId, int cartId);
	
	public List<OrderSummaryResponse> getOrdersForUser(int userId);
}
