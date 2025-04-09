package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import sg.edu.nus.team3.shoppingcart.model.Product;
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

	// @Override
	// public List<Order> findAllOrder() {
	// return orderRepository.findAll();
	// }

	@Override
	public Order createOrder(List<OrderItem> orderItems) {

		return new Order();
		// Order order = new Order();

		// for (OrderItem item : orderItems) {
		// Optional<Product> product = productRepository.findById();
		// }

	}

}
