package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.repository.OrderRepository;
import sg.edu.nus.team3.shoppingcart.repository.ProductRepository;
import sg.edu.nus.team3.shoppingcart.service.OrderService;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Order> findAllOrder() {
		return orderRepository.findAll();
	}

	//Pre-create order, for reviewing and selecting payment method before checking out
	@Override
	public Order preCreateOrder(List<OrderItem> orderItems, User user) {
		Order order = new Order();
		
		order.setOrderItems(orderItems);
		order.setUser(user);
		
		order.setStatus("PENDING");
		
		return order;

	}
	
	
	@Override
	public Order createOrder(Order order, User user) {
		
		//Order items are already in Order from preCreateOrder
		
		//May not need this? Can be done in SQL?
		LocalDateTime now = LocalDateTime.now();
		order.setCreateAt(now);
		
		order.setStatus("PAID");
		
		//Iterate through OrderItem list
		for (OrderItem item : order.getOrderItems()) {
			
			//Fetch the product by ID
			Optional<Product> optionalProduct 
				= productRepository.findById(item.getProduct().getId());
			
			//Check to make sure we have the product
			if (optionalProduct.isPresent()) {
				
				Product product = optionalProduct.get();
				
				if (product.getStock() >= item.getQuantity()) {
					
					//Remove from product if there's enough stock
					product.setStock(product.getStock() - item.getQuantity());
					
				} else {
					//throw exception - product stock not enough
				}
				
			} else {
				//throw exception - product not found
			}
			
		}
		return orderRepository.save(order);
		//return order;
			

	}
	
	/* no need?
	@Override
	public Order completeOrder(Order order) {
		order.setStatus("COMPLETED");
		
		LocalDateTime now = LocalDateTime.now();
		order.setFulfilmentDate(now);
		
		return orderRepository.save(order);
		//return order;
	}
	*/
	
	@Override
	public double calcTotal(List<OrderItem> orderItems) {
		
		double totalPrice = 0.0;
		
		//Iterate through OrderItem list
		for (OrderItem item: orderItems) {
			
			//Fetch the product by ID
			Optional<Product> optionalProduct = productRepository.findById(item.getProduct().getId());
			
			//Check to make sure we have the product
			if (optionalProduct.isPresent()) {
				
				Product product = optionalProduct.get();
				
				totalPrice += product.getUnitPrice()*item.getQuantity();
				
			} else {
				//throw exception - product not found
			}
		}
		return totalPrice;
	}
	
	
	//Get the current order
	@Override
	public Order getOrderById(int id) {
		Order orderToCheck = orderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not found"));
		return orderToCheck;
		//return List<Order> orderRepository.findById(id);
		//		.orElseThrow(() -> new RuntimeException("Order not found"));
	}
	//@Override
	//public User getUserByOrderId(int id) {
	//	return or
	//}
	
	
	
	//List all orders by user?
	//@Override
	//public List<Order> searchOrderHistory(User user) {
	//	return orderRepository.findByUser(user);
	//}
	
	//List orders made (by all users?) in the past week?
	//@Override
	//public List<Order>  getOrdersInWeek(Order order) {
	//


}
