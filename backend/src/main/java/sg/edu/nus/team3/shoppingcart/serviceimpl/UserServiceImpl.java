package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.team3.shoppingcart.exception.InvalidLoginException;
import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.repository.UserRepository;
import sg.edu.nus.team3.shoppingcart.service.UserService;

/**
@author diony
*/

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<User> findUserByEmail(String email) {
		return userRepo.findUserByEmail(email);

	}

	@Override
	public void registerUser(String email, String passwordInput, String handPhoneNo, String address, String firstName,
			String lastName) {
		
		// Hashing the user input password with PasswordEncoder
		String hashedPassword = passwordEncoder.encode(passwordInput);
		
		User user = new User();
		user.setEmail(email);
		
		// Encrypted version of password is stored instead of original password in plaintext
		user.setPassword(hashedPassword);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setHandPhoneNo(handPhoneNo);
		user.setAddress(address);
		
		userRepo.save(user);
	}

	@Override
	public boolean loginAttempt(String email, String passwordInput) {
		
		// Returns true of login attempt is successful when the user can be found by email and matches() returns true, and false if not
		Optional<User> userOpt = userRepo.findUserByEmail(email);
		
	    if (userOpt.isEmpty() || !passwordEncoder.matches(passwordInput, userOpt.get().getPassword())) {
	        throw new InvalidLoginException("Invalid email or password");
	    }
		
		return true;
	}

	@Override
	public Optional<User> findUserById(int id) {
		// TODO Auto-generated method stub
		return userRepo.findUserById(id);
	}

	@Override
	public User createUser(User user) {
		return userRepo.save(user);
		
	}

	@Override
	public boolean existsByEmail(String email) {
		Optional<User> userOpt = userRepo.findUserByEmail(email);
		
		if (userOpt.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public void registerUser(User user) {
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		
		userRepo.save(user);
	}

}
