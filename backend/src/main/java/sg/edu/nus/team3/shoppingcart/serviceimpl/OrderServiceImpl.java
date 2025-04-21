package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.projections.OrderProjection;
import sg.edu.nus.team3.shoppingcart.repository.OrderRepository;
import sg.edu.nus.team3.shoppingcart.repository.ProductRepository;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.repository.UserRepository;
import sg.edu.nus.team3.shoppingcart.service.OrderService;
import sg.edu.nus.team3.shoppingcart.util.DateUtil;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	// --------------------------------------------------------------
	// Author: Hiroyo
	// --------------------------------------------------------------

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private ShoppingCartServiceImplementation scService;

	@Override
	public Order checkoutCart(int userId, int cartId, String paymentMethod) {
		User user = userRepository.findById(userId).orElseThrow();
		ShoppingCart cart = shoppingCartRepository.findById(cartId).orElseThrow();

		// Check to make sure the cart is not empty
		if (cart.getItems().isEmpty()) {
			throw new IllegalStateException("Cart is empty");
		}

		// Create new order
		Order order = new Order();
		order.setUser(user);
		order.setStatus("PENDING");

		// Create orderItems and get the total price
		// Loop through shopping cart items and add each orderItem to order
		double total = 0.0;
		for (ShoppingCartItem item : cart.getItems()) {

			// Fetch the product by ID
			Product checkProduct = productRepository.findById(item.getProduct().getId())
					.orElseThrow(() -> new RuntimeException("Product not found: " + item.getProduct().getId()));

			// Check to make sure there's enough product stock
			if (checkProduct.getStock() < item.getQuantity()) {
				throw new RuntimeException("Not enough stock: " + checkProduct.getId());
			}

			// If there's enough stock, create an order and remove from product
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setUnitPrice(item.getProduct().getUnitPrice());

			total += orderItem.getUnitPrice() * orderItem.getQuantity();
			order.getOrderItems().add(orderItem);

			checkProduct.setStock(checkProduct.getStock() - item.getQuantity());
			productRepository.save(checkProduct);
		}

		// Set the total price
		// order.setTotalAmount(total);

		// Payment method selected
		order.setPaymentMethod(paymentMethod);// add validation?

		// Assume payment is successful
		order.setStatus("Completed");
		order.setCreateAt(LocalDateTime.now());
		order.setTotalAmount(total);

		// Set fulfilment date to 1 day later
		LocalDate later = order.getCreateAt().toLocalDate().plusDays(1);
		order.setFulfilmentDate(later);

		// Save order to repository
		orderRepository.save(order);

		// Clear cart at the end
		scService.deleteAllItemsInCart(cartId);
		shoppingCartRepository.save(cart);

		return order;

	}

	// Get the current order by order id
	@Override
	public Order getOrderById(int id) {
		Order orderToCheck = orderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not found"));
		return orderToCheck;
	}

	/*
	 * Jared's stuff
	 */

	public Optional<Order> getOptOrderById(int order_id) {
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

}
