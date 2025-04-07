package sg.edu.nus.team3.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.serviceimpl.UserServiceImpl;

@SpringBootTest
public class LoginTests {

	@Autowired
	private UserServiceImpl userService;
	
	@Test
	public void testSaveUser() {
		
	}
	
	@Test
	public void ExistingUserFoundByEmailIsNotNull(String email) {
		assertNotNull(userService.findUserByEmail("john.doe@example.com"));
	}
	
	
	@Test
	public void correctUserFoundByEmail(String email) {
		assertEquals(new User() userService.findUserByEmail("john.doe@example.com"));
	}
	
	
	@Test
	public void NonExistingUserFoundByEmailIsNull(String email) {
		
		assertNull(userService.findUserByEmail("nowaythisemailexists@gmail.com"));
		
	}
}
