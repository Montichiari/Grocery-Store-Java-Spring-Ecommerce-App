package sg.edu.nus.team3.shoppingcart.service;

import sg.edu.nus.team3.shoppingcart.model.User;

public interface UserService {
	
	public User findUserByEmail(String email);

}
