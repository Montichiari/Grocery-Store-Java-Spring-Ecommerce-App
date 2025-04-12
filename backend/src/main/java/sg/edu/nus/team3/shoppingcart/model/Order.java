package sg.edu.nus.team3.shoppingcart.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private User user;

	private LocalDateTime createAt;
	private LocalDateTime fulfilmentDate;
	private String status;
	private String paymentMethod;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems;
	
	private double totalAmount;

	// Empty constructor required by JPA
	public Order() {
		this.setOrderItems(new ArrayList<>());// Initialize with an empty list
	}

	// Constructors
	
	

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public Order(User user, LocalDateTime createAt, LocalDateTime fulfilmentDate, String status, String paymentMethod, double totalAmount) {
		super();
		this.user = user;
		this.createAt = createAt;
		this.fulfilmentDate = fulfilmentDate;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.orderItems = new ArrayList<OrderItem>();
		this.totalAmount = totalAmount;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getFulfilmentDate() {
		return fulfilmentDate;
	}

	public void setFulfilmentDate(LocalDate fulfilmentDate) {
		this.fulfilmentDate = fulfilmentDate;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", createAt=" + createAt + ", fulfilmentDate=" + fulfilmentDate
				+ ", status=" + status + ", paymentMethod=" + paymentMethod + "]";
	}

	// methods

}
