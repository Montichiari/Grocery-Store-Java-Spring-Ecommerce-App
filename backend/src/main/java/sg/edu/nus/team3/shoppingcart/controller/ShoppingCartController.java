package sg.edu.nus.team3.shoppingcart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;

@RestController
@CrossOrigin()
@RequestMapping("/cart")
public class ShoppingCartController {

	/*
	@PutMapping("/{id}/clear")
	public ResponseEntity<List<ShoppingCart>> clearCart() {
		
		
		
		return new ResponseEntity<List<ShoppingCart>> (clearedCart, HttpStatus.OK);
	}
	*/	
	
}
