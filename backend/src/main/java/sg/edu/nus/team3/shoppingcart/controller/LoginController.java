package sg.edu.nus.team3.shoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		//To insert: return view;
	}
	
	
	@PostMapping("/login")
	public String handleLogin(@RequestParam("username") String username, Model model, HttpSession session) {
		
		// Log users in if email exists in database, and associated password matches.
		if (username.equalsIgnoreCase("dipsa")) {
			session.setAttribute("username", username);
			
			return "redirect:/product/list";
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
