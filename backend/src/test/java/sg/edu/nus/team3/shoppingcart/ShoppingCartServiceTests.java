package sg.edu.nus.team3.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartItemRepository;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;

@SpringBootTest(classes = ShoppingcartApplicationTests.class)

public class ShoppingCartServiceTests {

	@Autowired
	private ShoppingCartService scService;
	// assert means that this is what u expect from the tests

	@Autowired
	private ShoppingCartRepository scRepo;

	@Autowired
	private ShoppingCartItemRepository scItemRepo;

	// test if can add item to shopping cart

	// test if can view item in shopping cart

	// test to create shopping cart

	// test to delete all items in shopping cart

}
