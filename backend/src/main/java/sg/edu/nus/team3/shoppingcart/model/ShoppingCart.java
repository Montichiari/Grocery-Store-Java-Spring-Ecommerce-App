package sg.edu.nus.team3.shoppingcart.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	@OneToOne
	private User user;



	@OneToMany(mappedBy = "shoppingCart")
	// shopping cart items has the foreign key of shopping cart id
	private List<ShoppingCartItem> items;

	@Getter
	@Setter
	private LocalDateTime upatedAt;

	// getters and setters for shopping cart items
	public List<ShoppingCartItem> getShoppingCartItems() {
		return items;
	}

	public void setShoppingCartItems(List<ShoppingCartItem> items) {
		this.items = items;

	}

}
