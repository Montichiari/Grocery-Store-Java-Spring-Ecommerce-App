package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.repository.ProductRepository;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;

@Service
@Transactional
public class ShoppingCartServiceImplementation implements ShoppingCartService {

	@Autowired
	private ShoppingCartRepository cartRepo;
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	@Transactional
	public void addShoppingCartItem(int user_id, int scart_item_id) {

	}

	@Override
	public List<ShoppingCartItem> viewShoppingCartItem(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCart createShoppingCart(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public void clearShoppingCart(int cartId) {
        ShoppingCart cart = cartRepo.findById(cartId).orElseThrow();

        cart.getItems().clear();
        cartRepo.save(cart);
    }

	@Override
	public void addToCart(int cartId, int productId, int qty) {
		
		 ShoppingCart cart = cartRepo.findById(cartId).orElseThrow();
		 Product product = productRepo.findById(productId).orElseThrow();

	        // Check if the product already exists in the cart
	        Optional<ShoppingCartItem> existingItem = cart.getItems().stream()
	            .filter(item -> item.getProduct().getId() == productId)
	            .findFirst();

	        if (existingItem.isPresent()) {
	        	ShoppingCartItem item = existingItem.get();
	            item.setQuantity(item.getQuantity() + qty);
	        } else {
	        	ShoppingCartItem newItem = new ShoppingCartItem();
	            newItem.setShoppingCart(cart);
	            newItem.setProduct(product);
	            newItem.setQuantity(qty);
	            cart.getItems().add(newItem);
	        }

	        cartRepo.save(cart); // cascade saves items too
	}

}
