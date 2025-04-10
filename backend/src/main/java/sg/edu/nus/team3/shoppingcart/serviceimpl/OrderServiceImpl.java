package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import sg.edu.nus.team3.shoppingcart.repository.OrderRepository;
import sg.edu.nus.team3.shoppingcart.service.OrderService;
import sg.edu.nus.team3.shoppingcart.util.DateUtil;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order createOrder(List<OrderItem> orderItems) {
		return new Order();
		// Order order = new Order();

		// for (OrderItem item : orderItems) {
		// Optional<Product> product = productRepository.findById();
		// }

	}

	public Optional<Order> getOrderById(int order_id) {
		Optional<Order> order = orderRepository.findById(order_id);
		return order;
	}

	public List<Order> getAllOrders() {
		List<Order> orderList = orderRepository.findAll();
		return orderList;
	}

	public List<Order> getWeeklyOrders() {
		LocalDateTime startDate = DateUtil.convertToLocalDateTime(new Date());
		LocalDateTime endDate = DateUtil.convertToLocalDateTime(DateUtil.subtractDays(new Date(), 7));
		List<Order> orderList = orderRepository.findWeeklyOrders(startDate, endDate).get();
		return orderList;
	}
}
