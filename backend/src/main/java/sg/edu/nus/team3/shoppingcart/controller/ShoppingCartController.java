package sg.edu.nus.team3.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.service.OrderService;

//@CrossOrigin
@RestController
@RequestMapping("/api")
public class ShoppingCartController {
	
	//Checkout cart
		@Autowired
		OrderService orderService;
		
		//Show review page (before payment)
		@GetMapping("/order")
		public ResponseEntity<String> displayOrderDetails(@RequestParam int id) {
			Order ordersToCheck = orderService.getOrderById(id);
			return new ResponseEntity<>(ordersToCheck.getPaymentMethod(), HttpStatus.OK);
		}
		
		//Select payment method

}
