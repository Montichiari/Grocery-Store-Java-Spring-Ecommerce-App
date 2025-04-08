package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sg.edu.nus.team3.shoppingcart.ShoppingcartApplicationTests;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartItemRepository;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;


@SpringBootTest(classes = ShoppingcartApplicationTests.class)

public class ShoppingCartRepositoryTest {

	@Autowired
	private ShoppingCartService scService; 
	// assert means that this is what u expect from the tests 

	@Autowired
	private ShoppingCartItemRepository scItemRepo; 
	
	
	@Test
	@DisplayName("Test to update shopping cart item quantity")
	public void testUpdateCartQuantity() {
	
		// Create a new shopping cart item
	    ShoppingCartItem item = new ShoppingCartItem();
	    // Set the quantity of the item to 1
	    item.setQuantity(1);
	    // Save the item into the repository
	    item = scItemRepo.save(item); 

	    // Update the item's quantity to 4 using the service
	    scService.updateCartQuantity(item.getId(), 4);

	    // Retrieve the updated item from the repository
	    ShoppingCartItem updatedItem = scItemRepo.findById(item.getId()).orElse(null);
	    
	    // Assert that the updated item is not null
	    assertThat(updatedItem).isNotNull();
	    // Assert that the quantity has been updated to 4
	    assertThat(updatedItem.getQuantity()).isEqualTo(4);
	 
		 
	}
		
		
		
		@Test
		@DisplayName("Test to delete items from cart")
		public void testDeleteItemsFromCart() {
			
			    
			}

	    
			
			
		
			
			

		
	
	
	
	
	
	

	

	
	
	
	
	
	
	



}
