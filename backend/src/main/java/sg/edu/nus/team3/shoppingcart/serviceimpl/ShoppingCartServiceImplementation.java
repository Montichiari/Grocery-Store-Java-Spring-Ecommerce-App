package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartItemRepository;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;
import java.util.Optional;
import sg.edu.nus.team3.shoppingcart.service.ProductService;
import sg.edu.nus.team3.shoppingcart.repository.ProductRepository;

// @ authored by @thina 

@Service
@Transactional
public class ShoppingCartServiceImplementation implements ShoppingCartService {

	@Autowired
	ShoppingCartRepository scRepo;

	@Autowired
	ShoppingCartItemRepository scItemRepo;

	@Override
	@Transactional
	public ShoppingCart findShoppingCartById(int id) {

		Optional<ShoppingCart> userShoppingCart = scRepo.findShoppingCartById(id);
		if (!(userShoppingCart.isPresent())) {
			throw new RuntimeException("Shopping cart not found for user id: " + id);
		} else {
			return userShoppingCart.get();
		}

	}

	@Override
	@Transactional
	public void saveShoppingCart(ShoppingCart userShoppingCart) {
		// save cart to database
		ShoppingCart savedShoppingCart = scRepo.save(userShoppingCart);

	}

	@Override
	@Transactional
	public void deleteAllItemsInCart(int cartId) {

		// use find cart by id method in the earlier part of this service implementation
		ShoppingCart userCart = findShoppingCartById(cartId);

		List<ShoppingCartItem> listCartItems = userCart.getItems();

		// delete all items in the cart from the database
		for (int i = 0; i < listCartItems.size(); i++) {
			// get each shopping cart item id and delete from the database
			ShoppingCartItem shopping_cart_item = listCartItems.get(i);
			// after getting each shopping cart item, delete each item from the database of
			// shopping cart item
			int shopping_cart_item_id = shopping_cart_item.getId();
			scItemRepo.deleteById(shopping_cart_item_id);
		}

		// clear memory of the list after removing from database
		listCartItems.removeAll(listCartItems);

	}

	public ShoppingCart addItemToCart(int shoppingCartId, int productId, int quantity){
	// find shopping cart by cart id 
	ShoppingCart userCart = findShoppingCartById(shoppingCartId); 
	// check if the product id exists in the database 
	Product product = ProductRepository.findProductById(productId);
	// get shopping cart , then get the list of the items in cart
	// if the list contains the product, update quantity of the product in the cart
	// else, add the product to the cart and set the quantity 
	





return ShoppingCart updated_cart ; 
}

}
