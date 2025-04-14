package sg.edu.nus.team3.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.service.UserService;

/**
 * @author Ma Li
 */

@RestController
@RequestMapping("/orders")
public class OrderHistoryController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/history")
	@ResponseBody
	public ResponseEntity<?> getOrderHistory(HttpSession session) {

		int userId = (int) session.getAttribute("id");

		if (userId == 0) {
			return new ResponseEntity<>("You must be logged in to view this page", HttpStatus.UNAUTHORIZED);
		}

		User user = userService.findUserById(userId);
		List<Order> orders = user.getOrders();

		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
}
