package sg.edu.nus.team3.shoppingcart.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.team3.shoppingcart.model.dto.AddToCartRequest;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;

@RestController
@CrossOrigin()
@RequestMapping("/cart")
public class ShoppingCartController {
	
	@Autowired
    private ShoppingCartService cartService;
	
	@Autowired
	private ShoppingCartRepository cartRepo;

	@GetMapping
	public ResponseEntity<?> viewCart(HttpSession session) {
	    int cartId = (int) session.getAttribute("cartId");

	    if (cartId == 0) {
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }

	    ShoppingCart cart = cartRepo.findById(cartId).get();

	    return new ResponseEntity<>(cart.getItems(), HttpStatus.OK);
	}
	
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartRequest request, HttpSession session) {
        int cartId = (int) session.getAttribute("cartId");

        if (cartId == 0) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        cartService.addToCart(cartId, request.getProductId(), request.getQuantity());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(HttpSession session) {
        int cartId = (int) session.getAttribute("cartId");

        if (cartId == 0) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        cartService.clearShoppingCart(cartId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	/*
	@PutMapping("/{id}/clear")
	public ResponseEntity<List<ShoppingCart>> clearCart() {
		
		
		
		return new ResponseEntity<List<ShoppingCart>> (clearedCart, HttpStatus.OK);
	}
	*/	
	
}
