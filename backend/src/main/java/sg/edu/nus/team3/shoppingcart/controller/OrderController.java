package sg.edu.nus.team3.shoppingcart.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	
	//-----------------------------------------------
	// Author: Hiroyo
	//-----------------------------------------------
	
	@Autowired
	private OrderService orderService;
	
	// Create order, ask for payment method, "pay", complete order
	@PostMapping("/checkout")
	public ResponseEntity<Order> checkoutCartAndSaveOrder(HttpSession session, @RequestParam String paymentMethod) {
		
		Integer userId = (Integer) session.getAttribute("userId");//is it "id" or "userId"?
		Integer cartId = (Integer) session.getAttribute("cartId");
		
		//check with hard coded values
		userId = 1;
		cartId = 1;
		
		// Throw an error if the user or the cart doesn't exist
		if (userId == null || cartId == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			// Create order from userId and cartId
			Order order = orderService.checkoutCart(userId, cartId);
			
			// Payment method selected
			order.setPaymentMethod(paymentMethod);//add validation (use enum?)
			
			// Assume payment is successful
			order.setStatus("COMPLETED");
			// Set fulfilment date to 1 day later
			LocalDate later = order.getCreateAt().toLocalDate().plusDays(1);
			order.setFulfilmentDate(later);
			
			// Save order
			orderService.saveOrder(order);
			
			// Update product entity
			orderService.updateProductStock(order);
			
			return new ResponseEntity<>(order,HttpStatus.OK);
			
		} catch (IllegalStateException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	// Display order confirmation
	@GetMapping("/confirmation")
	public ResponseEntity<?> getOrderConfirmation(@RequestParam int orderId, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		// Make sure the user is logged in
		if (userId == null) {
			return new ResponseEntity<>(Map.of("error", "User not logged in"),HttpStatus.UNAUTHORIZED);
		}
		
		Order order = orderService.getOrderById(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	//-----------------------------------------------
	
	
	

}
