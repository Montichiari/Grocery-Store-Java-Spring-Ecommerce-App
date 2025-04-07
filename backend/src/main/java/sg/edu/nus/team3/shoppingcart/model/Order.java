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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private int id;
	
	//ManyToOne(mappedBy = "order")
	//@Getter @Setter
	//private int customerId;
	@ManyToOne
	@Getter @Setter
	private User user;
	
	@Getter @Setter
	private LocalDateTime createAt;
	@Getter @Setter
	private LocalDate fulfilmentDate;
	@Getter @Setter
	private String status;
	@Getter @Setter
	private String paymentMethod;
	
	@ManyToMany
	@JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"), 
		inverseJoinColumns = @JoinColumn(name = "product_id"))
	@Getter @Setter
	private List<Product> products;

	//Empty constructor required by JPA
	public Order() {
		this.products = new ArrayList<>();//Initialize with an empty list
	}
	
	//Constructors
	public Order(User user, LocalDateTime createAt, LocalDate fulfilmentDate, 
			String status, String paymentMethod, List<Product> products) {
		
		this.user = user;
		this.createAt = createAt;
		this.fulfilmentDate = fulfilmentDate;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.products = products;

	}
	
	
	//methods
	
	

}
