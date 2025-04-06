package sg.edu.nus.team3.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.service.UserService;

/**
@author diony
*/

@RestController
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		//To insert: return view;
	}
	
	
	@PostMapping("/login")
	public String handleLogin(@RequestParam("email") String username, @RequestParam("password") String password, Model model, HttpSession session) {
		
		// Log users in if email exists in database, and associated password matches
		// on successful login, updates session with "username" and "role" attributes
		
		User user = userService.findUserByEmail("email");
				
		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("username", username);
			session.setAttribute("role", user.getRole());
			
			return "redirect:/";
		}
		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
	    // Deletes all information in current session, and locks users out of application till next login
		session.invalidate();
		
		
		// To insert: return view;
	}
	
}
