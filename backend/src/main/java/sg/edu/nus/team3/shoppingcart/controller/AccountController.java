package sg.edu.nus.team3.shoppingcart.controller;

import java.util.List;

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
import sg.edu.nus.team3.shoppingcart.model.dto.RegisterRequest;
import sg.edu.nus.team3.shoppingcart.model.dto.UpdateUserRequest;
import sg.edu.nus.team3.shoppingcart.service.UserService;

/**
@author Dion Yao
*/

@RestController
@CrossOrigin()
@RequestMapping("/account")
public class AccountController {


	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register/staff")
	public ResponseEntity<?> registerStaff(@Valid @RequestBody RegisterRequest request) {
		
		try {
			User newUser = userService.registerStaff(request);
			return new ResponseEntity<User> (newUser, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Unable to register staff account", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable("id") int accountId, HttpSession session) {

		// Check if user is staff
	    String role = (String) session.getAttribute("role");
	    boolean isStaff = role.equalsIgnoreCase("staff");
	    
	    if (isStaff == false) {
	    	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	    }

	    User user = userService.findUserById(accountId);
	    return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers(HttpSession session) {
	    
		
		// Check if user is staff
	    String role = (String) session.getAttribute("role");
	    boolean isStaff = role.equalsIgnoreCase("staff");
	    
	    if (isStaff == false) {
	    	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	    }

	    List<User> user = userService.findAll();
	    return new ResponseEntity<>(user, HttpStatus.OK);
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
	    	session.invalidate();
		    return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	    
	}
}
