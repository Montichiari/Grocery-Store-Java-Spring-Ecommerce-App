package sg.edu.nus.team3.shoppingcart.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.model.dto.LoginRequest;
import sg.edu.nus.team3.shoppingcart.model.dto.RegisterRequest;
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
		// on successful login, updates session with "email" and "role" attributes
		
		if (userService.loginAttempt(request)) {
			User user = userOpt.get();
			
			session.setAttribute("id", user.getId());
			session.setAttribute("role", user.getRole());
						
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	
	@GetMapping("/status")
	public ResponseEntity<?> checkLoginStatus(HttpSession session) {
		Integer userId = (Integer) session.getAttribute("id");
		
		if (userId != null) {
			return new ResponseEntity<Integer>((Integer) session.getAttribute("id"), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	//public ResponseEntity<Map<String, Object>> checkLoginStatus(HttpSession session) {
		//Object id = session.
	//}
	
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
	
	
	/*
	
	@PostMapping("/login")
	public String handleLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		
		// Log users in if email exists in database, and associated password matches
		// on successful login, updates session with "email" and "role" attributes
		
		if (userService.loginAttempt(email, password)) {
			session.setAttribute("id", userService.findUserByEmail(email).get().getId());
			session.setAttribute("role", userService.findUserByEmail(email).get().getRole());
			
			return "redirect:/";
		}
		
		return "login";
	}
	
	*/
}
