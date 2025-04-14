package sg.edu.nus.team3.shoppingcart.model.dto;

/**
 * @authored Dion Yao
 */

public class UpdateUserRequest {
	
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private String handPhoneNo;
	private String password;
	
	
	public UpdateUserRequest() {
		
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getHandPhoneNo() {
		return handPhoneNo;
	}


	public void setHandPhoneNo(String handPhoneNo) {
		this.handPhoneNo = handPhoneNo;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	
	
	
}
