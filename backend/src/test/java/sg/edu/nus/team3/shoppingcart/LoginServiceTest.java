package sg.edu.nus.team3.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.team3.shoppingcart.serviceimpl.UserServiceImpl;

@SpringBootTest
public class LoginServiceTest {

	@Autowired
	private UserServiceImpl userService;

	@Test
	public void ExistingUserFoundByEmailIsNotNull() {
		assertTrue(userService.findUserByEmail("john.doe@email.com").isPresent());
	}

	@Test
	public void NonExistingUserFoundByEmailIsNull() {

		assertTrue(userService.findUserByEmail("nowaythisemailexists@gmail.com").isEmpty());

	}

	@Test
	public void loginAttemptReturnsFalseWhenNonExistingEmail() {

		assertFalse(userService.loginAttempt("nowaythisemailexists@gmail.com", "password123")); // Password exists in
																								// database for user
																								// "john.doe@example.com"
	}

	@Test
	public void loginAttemptReturnsFalseWhenPasswordMisMatch() {

		assertFalse(userService.loginAttempt("john.doe@email.com", "notrightpassword"));
	}

	@Test
	public void loginAttemptReturnsTrueWhenEmailExistsAndPasswordMatch() {

		assertTrue(userService.loginAttempt("john.doe@email.com", "Password123!"));
	}

	@Test
	public void logoutRemovesSessionData() {
		// To implement
	}

}
