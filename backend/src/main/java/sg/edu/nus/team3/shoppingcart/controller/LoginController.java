package sg.edu.nus.team3.shoppingcart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@author Dion Yao
*/

@RestController
@CrossOrigin()
@RequestMapping("/user")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	
	/* 
	 * This section is for Login / Login status / Logout
	 * 
	 */
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
		
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
		
		return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
	}
	
	
	@GetMapping("/status")
	public ResponseEntity<?> checkLoginStatus(HttpSession session) {
		
		Object userId = session.getAttribute("id");
		
		if (userId == null) {
			return new ResponseEntity<>("You are not logged in", HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("You are logged in", HttpStatus.OK);
		
	}
	
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		
	    // Deletes all information in current session, and locks users out of application till next login
		session.invalidate();
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/register/customer")
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody RegisterRequest request) {
		
		
		try {
			User newUser = userService.registerCustomer(request);
			return new ResponseEntity<User> (newUser, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Unable to register account", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") int accountId, @RequestBody UpdateUserRequest request, HttpSession session) {
	    
		// Get userId
		int userId = (int) session.getAttribute("id");

	    
	    // Checking if the user is staff or not
	    String userRole = (String) session.getAttribute("role");
	    boolean isStaff = userRole.equalsIgnoreCase("staff");
	    
	    // If not staff, they are not allowed to update account that isn't their own
	    if (isStaff == false && accountId != userId) {
	    	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	    }

	    User updatedUser = userService.updateUser(accountId, request);
	    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int accountId, HttpSession session) {
	    
		// Get userId
		int userId = (int) session.getAttribute("id");

	    
	    // Checking if the user is staff or not
	    String userRole = (String) session.getAttribute("role");
	    boolean isStaff = userRole.equalsIgnoreCase("staff");
	    
	    
	    // If not staff, they are not allowed to delete account that isn't their own
	    if (isStaff == false && accountId != userId) {
	    	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	    }
	    
	    // User deleted and session invalidated. Put in a try-catch for security
	    try {
	    	userService.deleteUser(accountId);
	    	if (accountId == userId) {
	    		session.invalidate();
	    	}
		    return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	    
	}
	
}
