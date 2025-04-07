package model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.repository.OrderRepository;
import sg.edu.nus.team3.shoppingcart.repository.ProductRepository;
import sg.edu.nus.team3.shoppingcart.serviceimpl.OrderServiceImpl;


@SpringBootTest
public class OrderServiceTest {
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	//@Override
	public List<Order> findAllOrder() {
		return orderRepository.findAll();
	}
	
	//@Transactional(readOnly = false)
	//@Override
	public Order createOrder(List<OrderItem> orderItems) {
		
		Order order = new Order();
		int productId;
		//Product product = new Product();
		//List<Product> checkProducts = productRepository.findAllById(productIds);
		
		
		for (OrderItem orderItem: orderItems) {
			order.setOrderItem(orderItems);
			
			productId = orderItems.getProduct().getId;
			Product product = productRepository.find;
			
			int stock = product.getStock();
			product.setStock(product.getStock() - orderItems.getQuantity());

		}
		
		
	}
		
		
		
	

}
