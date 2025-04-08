package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;


@SpringBootTest(classes = ShoppingcartApplicationTests.class)

public class ShoppingCartServiceTests {

	@Autowired
	private ShoppingCartService scService; 
	// assert means that this is what u expect from the tests 
	
	@Autowired
	private ShoppingCartRepository scRepo; 

	@Autowired
	private ShoppingCartItemRepository scItemRepo; 
	
	
	
	
	@Test 
	@DisplayName("Test to create a shopping cart ")
	public void checkIfShoppingCartExists() {
		//create shopping cart 
		ShoppingCart shoppingCart = new ShoppingCart(); 
		
		// add items into the shopping cart 
		ShoppingCartItem item = new ShoppingCartItem(); 
		
		// check if the shoppingcart exists by checking if its id exists 
		assertThat(scRepo.findById(shoppingCart.getId()).isPresent()); 
		//check if the shoppingcart item exists in the cart 
		assertThat(scItemRepo.findById(item.getId()).isPresent()); 
	
	}
	
	
	@Test 
	@DisplayName("Test to delete the entire shopping cart")
	public void checkIfCanDeleteEntireShoppingCart() {
		// create shopping cart 
		ShoppingCart shoppingCart = new ShoppingCart(); 
		
		// save the cart to the database 
		shoppingCart = scRepo.save(shoppingCart); 
		
		// add items to the cart
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		
		//save the shopping cart item to the database
		
		// remove the shopping cart from the database
		scRepo.delete(shoppingCart);
	
		
		// check if the shopping cart exists by checking if its id exists
		Optional<ShoppingCart> deletedCart = scRepo.findById(shoppingCart.getId()); 
		
		// should not be able to find this shopping cart after it has been deleted
		assertFalse(deletedCart.isPresent()); 
		
		// deleting the entire shopping cart should result in the items in the shopping cart being deleted as well
		
		 
	}
	
	
	
	@Test
	@DisplayName("Test to update shopping cart item quantity")
	public void testUpdateCartQuantity() {
	
		// Create a new shopping cart item
	    ShoppingCartItem item = new ShoppingCartItem();
	    // Set the quantity of the item to 1
	    item.setQuantity(1);
	    // Save the item into the database
	    // assign this to the item so that item reflects the newest version of the item
	    // when save the entity, JPA may update the entity eg return the auto generated ID in the scitem class 
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
			//create new shopping cart item
			ShoppingCartItem item = new ShoppingCartItem(); 
			
			// set quantity of item
			item.setQuantity(1);
			
			//save items to the sc database 
			item = scItemRepo.save(item); 
			
			
			// remove item from the database 
			scItemRepo.delete(item);
			
			// i should not be able to find the item 
			assertFalse(scItemRepo.findById(item.getId()).isPresent()); 
			
			    
			}

		
		
	    
			
			
		
			
			

		
	
	
	
	
	
	

	

	
	
	
	
	
	
	



}
