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
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.projections.OrderProjection;
import sg.edu.nus.team3.shoppingcart.repository.OrderRepository;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.repository.UserRepository;
import sg.edu.nus.team3.shoppingcart.service.OrderService;
import sg.edu.nus.team3.shoppingcart.util.DateUtil;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ShoppingCartRepository cartRepo;

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

	public List<OrderProjection> getWeeklyOrders() {
		LocalDateTime endDate = DateUtil.convertToLocalDateTime(new Date());
		LocalDateTime startDate = DateUtil.convertToLocalDateTime(DateUtil.subtractDays(new Date(), 7));
		List<OrderProjection> orderList = orderRepository.findWeeklyOrders(startDate, endDate).get();
		return orderList;
	}

	@Override
	public Order checkout(int userId, int cartId) {

		User user = userRepo.findById(userId).orElseThrow();
		ShoppingCart cart = cartRepo.findById(cartId).orElseThrow();

		if (cart.getItems().isEmpty()) {
			throw new IllegalStateException("Cart is empty");
		}

		Order order = new Order();
		order.setUser(user);
		order.setCreateAt(LocalDateTime.now());
		order.setStatus("pending");

		double total = 0.0;
		for (ShoppingCartItem item : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setUnitPrice(item.getProduct().getUnitPrice());

			total += orderItem.getUnitPrice() * orderItem.getQuantity();
			order.getOrderItems().add(orderItem);
		}

		order.setTotalAmount(total);
		orderRepository.save(order);

		// Clear cart
		cart.getItems().clear();
		cartRepo.save(cart);

		return order;
	}

}
