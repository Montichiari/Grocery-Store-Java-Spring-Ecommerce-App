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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private int id;


	@ManyToOne
	@Getter
	@Setter
	private User user;
	@Getter
	@Setter
	private LocalDateTime createAt;
	@Getter
	@Setter
	private LocalDate fulfilmentDate;
	@Getter
	@Setter
	private String status;
	@Getter
	@Setter
	private String paymentMethod;


	@OneToMany(mappedBy = "order")
	@Getter
	@Setter
	private List<OrderItem> orderItems;

	// Empty constructor required by JPA
	public Order() {
		this.orderItems = new ArrayList<>();// Initialize with an empty list
	}

	// Constructors
	public Order(User user, LocalDateTime createAt, LocalDate fulfilmentDate,
			String status, String paymentMethod, List<OrderItem> orderItems) {
		this.user = user;
		this.createAt = createAt;
		this.fulfilmentDate = fulfilmentDate;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.orderItems = orderItems;
	}



	// methods

}
