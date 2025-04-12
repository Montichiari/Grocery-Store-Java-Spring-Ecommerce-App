package sg.edu.nus.team3.shoppingcart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.dto.OrderSummaryResponse;
import sg.edu.nus.team3.shoppingcart.serviceimpl.OrderServiceImpl;

@RestController
@CrossOrigin()
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderService;
	
	@PostMapping("/checkout")
	public ResponseEntity<?> checkout(HttpSession session) {
	    // Get userId from session
	    int userId = (int) session.getAttribute("id");

	    if (userId == 0) {
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }

	    // Get cartId from session
	    int cartId = (int) session.getAttribute("cartId");

	    try {
	        Order order = orderService.checkout(userId, cartId);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	    
	}
	
    @GetMapping
    public ResponseEntity<?> getOrderHistory(HttpSession session) {
    	int userId = (int) session.getAttribute("id");

        if (userId == 0) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<OrderSummaryResponse> orders = orderService.getOrdersForUser(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
	
	
}
