package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.team3.shoppingcart.exception.InvalidLoginException;
import sg.edu.nus.team3.shoppingcart.mapper.UserMapper;
import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.model.dto.LoginRequest;
import sg.edu.nus.team3.shoppingcart.model.dto.RegisterRequest;
import sg.edu.nus.team3.shoppingcart.repository.UserRepository;
import sg.edu.nus.team3.shoppingcart.service.UserService;

/**
 * @author diony
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserMapper userMap;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Optional<User> findUserByEmail(String email) {
		return userRepo.findUserByEmail(email);

	}

	@Override
	public boolean loginAttempt(LoginRequest request) {

		String email = request.getEmail();
		String passwordInput = request.getPassword();

		// Returns true of login attempt is successful when the user can be found by
		// email and matches() returns true, and false if not
		Optional<User> userOpt = userRepo.findUserByEmail(email);

		if (userOpt.isEmpty() || !passwordEncoder.matches(passwordInput, userOpt.get().getPassword())) {
			throw new InvalidLoginException("Invalid email or password");
		}

		return true;
	}

	@Override
	public boolean loginAttempt(String email, String passwordInput) {

		// Returns true of login attempt is successful when the user can be found by
		// email and matches() returns true, and false if not
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
	public boolean existsByEmail(String email) {
		Optional<User> userOpt = userRepo.findUserByEmail(email);

		if (userOpt.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public User registerCustomer(RegisterRequest request) {

		User custToRegister = userMap.toUser(request);
		// Hashing the user input password with PasswordEncoder
		String hashedPassword = passwordEncoder.encode(custToRegister.getPassword());

		// Encrypted version of password is stored instead of original password in
		// plaintext
		custToRegister.setPassword(hashedPassword);

		// Role set to customer
		custToRegister.setRole("customer");

		return userRepo.save(custToRegister);
	}

	@Override
	public User registerStaff(RegisterRequest request) {

		User staffToRegister = userMap.toUser(request);
		// Hashing the user input password with PasswordEncoder
		String hashedPassword = passwordEncoder.encode(staffToRegister.getPassword());

		// Encrypted version of password is stored instead of original password in
		// plaintext
		staffToRegister.setPassword(hashedPassword);
		staffToRegister.setRole("staff");

		// Role set to staff. Will be able to access admin dashboard.
		return userRepo.save(staffToRegister);
	}

}
