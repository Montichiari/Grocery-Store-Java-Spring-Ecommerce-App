package sg.edu.nus.team3.shoppingcart.mapper;

import org.springframework.stereotype.Component;

import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.model.dto.RegisterRequest;

@Component
public class UserMapper {

	public User toUser(RegisterRequest dto) {
		
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setHandPhoneNo(dto.getHandPhoneNo());
		user.setAddress(dto.getAddress());
		
		return user;
	}
	
	
	
}
