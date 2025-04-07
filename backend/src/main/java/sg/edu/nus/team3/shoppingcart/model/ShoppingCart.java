package sg.edu.nus.team3.shoppingcart.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity 
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter 
	private int id;
	
	@Getter @Setter
	private User user; 
	
	@Getter
	private String sessionId;

	private ArrayList<ShoppingCartItems> items; 
	
	@Getter @Setter
	private LocalDateTime upatedAt;
	
	
	//getters and setters for shopping cart items 
	public List<ShoppingCartItems> getShoppingCartItems(){
		return items; 
	}
	
	public void setShoppingCartItems(List<ShoppingCartItems>items) {
		this.items = items; 
	}
	
	
	
	
	
	
	
	
	
}
