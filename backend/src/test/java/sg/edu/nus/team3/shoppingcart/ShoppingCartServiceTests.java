package sg.edu.nus.team3.shoppingcart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartItemRepository;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.repository.UserRepository;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartItemService;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ShoppingCartItemServiceImpl;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.model.User;

@SpringBootTest(classes = ShoppingcartApplicationTests.class)

public class ShoppingCartServiceTests {

	@Autowired
	private ShoppingCartService sc_ServiceImpl;
	// assert means that this is what u expect from the tests

	@Autowired
	private ShoppingCartRepository scRepo;

	@Autowired
	private ShoppingCartItemRepository scItemRepo;

	@Autowired
	private ShoppingCartItemService scItemService;

	@Autowired
	private UserRepository userRepo;

	// test if can add item in shopping cart

	@Test
	@DisplayName("Test if can add items to shopping cart")
	public void testAddItemToShoppingCart() {

		User new_user = new User();
		new_user = userRepo.save(new_user);
		// retrieve user shopping cart using method
		ShoppingCart user_shopping_cart = sc_ServiceImpl.findShoppingCartByUserId(new_user.getId());

		// create shopping cart item
		Product product = new Product();
		product.setCategory("vegetable");
		product.setId(1);
		product.setName("carrot");
		product.setStock(10);
		product.setUnitPrice(2.5);
		ShoppingCartItem new_item = new ShoppingCartItem();
		new_item.setProduct(product);
		new_item.setQuantity(1);
		// // save shoppingcart item in database
		scItemRepo.save(new_item);
		// // add item to the cart
		user_shopping_cart.getItems().add(new_item);
		// // save shopping cart in database
		scRepo.save(user_shopping_cart);
		// // assert that shopping cart has one item
		assertThat(user_shopping_cart.getItems().size()).isEqualTo(1);

	}

	// @Test
	// @DisplayName("Test if can view items in shopping cart")
	// public void testAddShoppingCartItem() {
	// // create new user
	// User new_user = new User();
	// // retrieve user shopping cart
	// new_user.getShoppingCart();
	// // add item to shopping cart

	// // assert that the shopping cart now has one item

	// }

	// @Test
	// @DisplayName("Test if can delete all items in shopping cart")
	// public void testClearShoppingCart() {
	// }
	// // test to delete all items in shopping cart

}
