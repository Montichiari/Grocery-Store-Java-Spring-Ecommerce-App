package sg.edu.nus.team3.shoppingcart.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import sg.nus.iss.testshop.model.Product;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//ManyToOne(mappedBy = "order")
	private int customerId;
	
	private LocalDateTime createAt;
	private LocalDate fulfillmentDate;
	private String status;
	private String paymentMethod;
	
	@ManyToMany
	@JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"), 
		inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;

	//Empty constructor required by JPA
	public Order() {
		this.products = new ArrayList<>();//Initialize with an empty list
	}
	
	//Constructors
	public Order(int customerId, LocalDateTime createAt, LocalDate fulfillmentDate, 
			String status, String paymentMethod, List<Product> products) {
		
		this.customerId = customerId;
		this.createAt = createAt;
		this.fulfillmentDate = fulfillmentDate;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.products = products;

	}
	
	//Getters and setters

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
	public LocalDate getFulfillmentDate() {
		return fulfillmentDate;
	}
	public void setFulfillmentDate(LocalDate fulfillmentDate) {
		this.fulfillmentDate = fulfillmentDate;
	}

	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
