package sg.edu.nus.team3.shoppingcart.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.model.dto.LoginRequest;
import sg.edu.nus.team3.shoppingcart.model.dto.RegisterRequest;
import sg.edu.nus.team3.shoppingcart.model.dto.UpdateUserRequest;
import sg.edu.nus.team3.shoppingcart.service.UserService;

/**
@author diony
*/

@RestController
@CrossOrigin()
@RequestMapping("/user")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> handleLogin(@Valid @RequestBody LoginRequest request, HttpSession session) {
		
		Optional<User> userOpt = userService.findUserByEmail(request.getEmail());
		
		// Log users in if email exists in database, and associated password matches
		// on successful login, updates session with "id", "role", and "cartId" attributes
		
		if (userService.loginAttempt(request)) {
			User user = userOpt.get();
			
			session.setAttribute("id", user.getId());
			session.setAttribute("role", user.getRole());
			session.setAttribute("cartId", user.getShoppingCart().getId());
						
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	
	@GetMapping("/status")
	public ResponseEntity<?> checkLoginStatus(HttpSession session) {
		int userId = (int) session.getAttribute("id");
		
		if (userId != 0) {
			return new ResponseEntity<Integer>((Integer) session.getAttribute("id"), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	
	@PostMapping("/register/customer")
	public ResponseEntity<User> registerCustomer(@Valid @RequestBody RegisterRequest request) {
		
		// Log users in if email exists in database, and associated password matches
		// on successful login, updates session with "email" and "role" attributes
		
		try {
			User newUser = userService.registerCustomer(request);
			return new ResponseEntity<User> (newUser, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/register/staff")
	public ResponseEntity<User> registerStaff(@Valid @RequestBody RegisterRequest request) {
		
		// Log users in if email exists in database, and associated password matches
		// on successful login, updates session with "email" and "role" attributes
		
		try {
			User newUser = userService.registerStaff(request);
			return new ResponseEntity<User> (newUser, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		
	    // Deletes all information in current session, and locks users out of application till next login
		session.invalidate();
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest request, HttpSession session) {
	    int userId = (int) session.getAttribute("id");

	    if (userId == 0) {
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }

	    User updatedUser = userService.updateUser(userId, request);
	    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> deleteUser(HttpSession session) {
	    int userId = (int) session.getAttribute("id");

	    if (userId == 0) {
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }
	    
	    // User deleted and session invalidated
	    userService.deleteUser(userId);
	    session.invalidate();

	    return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
