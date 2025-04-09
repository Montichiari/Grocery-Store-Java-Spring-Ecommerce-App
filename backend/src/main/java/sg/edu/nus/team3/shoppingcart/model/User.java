package sg.edu.nus.team3.shoppingcart.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author diony
 */

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Email is required")
	// Custom email validation to be created
	@Column(length = 320)
	private String email;

	@NotBlank(message = "Handphone no. is required")
	@Size(min = 8, max = 8, message = "Handphone no. must be 8 digits")
	@Column(name = "handphone_no", length = 8)
	private String handPhoneNo;

	@NotBlank(message = "Address is required")
	// Custom address validation to be created
	private String address;

	// Limit to 35 chars for first name and last name
	@NotBlank(message = "First name is required")
	@Size(max = 35, message = "First name must not be more than 35 characters")
	@Column(name = "first_name", length = 35)
	private String firstName;

	// Limit to 35 chars for first name and last name
	@NotBlank(message = "Last name is required")
	@Size(max = 35, message = "Last name must not be more than 35 characters")
	@Column(name = "last_name", length = 35)
	private String lastName;

	@OneToMany(mappedBy = "user")
	private List<Order> orders;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 128, message = "Password must be at least 8 characters long")
	// Custom password validation to be created
	private String password;

	private String role;

	@OneToOne
	private ShoppingCart shoppingCart;

	// Constructors

	// Empty Constructor
	public User() {

	}

	// Constructor for Customers to create Customer account
	public User(String email, String handPhoneNo, String address, String firstName, String lastName, String password) {
		super();
		this.email = email;
		this.handPhoneNo = handPhoneNo;
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = "Customer";
	}

	// Constructor Staff to create Staff OR Customer account
	public User(String email, String handPhoneNo, String address, String firstName, String lastName, String password,
			String role) {
		super();
		this.email = email;
		this.handPhoneNo = handPhoneNo;
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
	}

	// Getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHandPhoneNo() {
		return handPhoneNo;
	}

	public void setHandPhoneNo(String handPhoneNo) {
		this.handPhoneNo = handPhoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", handPhoneNo=" + handPhoneNo + ", address=" + address
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", role=" + role
				+ ", shoppingCart=" + shoppingCart + "]";
	}

	// To update toString once other classes are created

}
