package sg.edu.nus.team3.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import sg.edu.nus.team3.shoppingcart.repository.UserRepository;

@DataJpaTest
public class LoginTests {

	@Autowired
	private UserRepository userService;
	
	@Test
	public void testSaveUser() {
		
	}
	
	@Test
	public void userFoundByEmailNotNull(String email) {
		assertNotNull(userService.findUserByEmail("john.doe@example.com"));
	}
	
	/*
	@Test
	public void correctUserFoundByEmail(String email) {
		assertEquals(new User() userService.findUserByEmail("john.doe@example.com"));
	}
	*/
	
	@Test
	public void testUserNotFound(String email) {
		
		assertNull(userService.findUserByEmail("nowaythisemailexists@gmail.com"));
		
	}
}
