package sg.edu.nus.team3.shoppingcart.service;

import java.util.Optional;

import sg.edu.nus.team3.shoppingcart.model.User;

/**
@author diony
*/

public interface UserService {
	
	public Optional<User> findUserByEmail(String email);
	
	public Optional<User> findUserById(int id);
	
	public void registerUser(String email, String passwordInput, String handPhoneNo, String address, String firstName, String lastName);
	
	public boolean loginAttempt(String email, String passwordInput);
	
	
}
