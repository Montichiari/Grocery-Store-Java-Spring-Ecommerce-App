package sg.edu.nus.team3.shoppingcart.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.service.OrderService;
import sg.edu.nus.team3.shoppingcart.service.UserService;

@RestController
@CrossOrigin()
@RequestMapping("/orders")
public class OrderController {

	
	//author: Hiroyo-------------------------------------
	//Checkout cart
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	
	//Retrieve userId from the session and create new order from orderItems 
	//(assuming that there's a method that returns orderItems before this?)
	@PostMapping("/order")
	public ResponseEntity<Order> createNewOrderForUser(HttpServletRequest request, @RequestBody List<OrderItem> orderItems) {
		int userId = (Integer) request.getSession().getAttribute("userId");
		User user = userService.findUserById(userId);
		if (user==null) {
			//return other HttpStatus?
		}
		Order newOrder = orderService.preCreateOrder(orderItems,user);//This only adds orderItems and user to order
		return new ResponseEntity<>(newOrder, HttpStatus.OK);
	}
	
	
	
	//Show review page (before payment) - retrieve the order just created - do we need this method?
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> displayOrderDetails(@PathVariable int orderId) {
		Order ordersToCheck = orderService.getOrderById(orderId);
		if (ordersToCheck==null) {
			//return other HttpStatus?
		}
		return new ResponseEntity<>(ordersToCheck, HttpStatus.OK);
	}
	
	//Select payment method and complete order
	@PatchMapping("/order/{id}")
	public ResponseEntity<Order> completeAndSaveOrder(@PathVariable int orderId, @RequestBody Order orderRequest) {
		Order orderToComplete = orderService.getOrderById(orderId);
		
		if (orderToComplete==null) {
			//return other HttpStatus?
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		//save payment method
		orderToComplete.setPaymentMethod(orderRequest.getPaymentMethod());
		
		//update product (remove the stock from product)
		orderService.updateProductStock(orderToComplete);
		
		//save current time
		LocalDateTime now = LocalDateTime.now();
		orderToComplete.setCreateAt(now);
		
		//update status
		orderToComplete.setStatus("COMPLETE");
		
		//enter fulfilment date (just put 1 day later since it won't be used in this work anyway)
		LocalDate later = now.toLocalDate().plusDays(1);//since fulfilmentDate is LocalDate, not LocalDateTime but can change later.
		orderToComplete.setFulfilmentDate(later);
		
		//save updated order
		Order updatedOrder = orderService.saveOrder(orderToComplete);
		
		//return updated order
		return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
	}
	
	//Empty cart?
	
	//----------------------------------
}
