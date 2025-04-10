package sg.edu.nus.team3.shoppingcart.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpSession;

import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartItemRepository;
import sg.edu.nus.team3.shoppingcart.repository.ShoppingCartRepository;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ShoppingCartItemServiceImpl;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ShoppingCartServiceImplementation;
import sg.edu.nus.team3.shoppingcart.service.UserService;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartServiceImplementation shoppingCartService;

    @Autowired
    private ShoppingCartItemServiceImpl shoppingCartItemService;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @GetMapping("/items")
    public ResponseEntity<List<ShoppingCartItem>> getItemsInCart(HttpSession session) {
        int userId = (int) session.getAttribute("id");
        List<ShoppingCartItem> items = shoppingCartService.findShoppingCartByUserId(userId).getItems();
        return new ResponseEntity<List<ShoppingCartItem>>(items, HttpStatus.OK);
    }

    @PostMapping("/add-item/{product_id}")
    public ResponseEntity<List<ShoppingCartItem>> addItemToCart(@PathVariable Product product_item, int quantity,
            HttpSession session) {
        // do if else statement here if the user id is null/not valid , then reject the
        // user and return error message
        // else continue with the flow
        // get user id from session object
        int user_id_from_session = (int) session.getAttribute("id");
        // find shopping cart by user Id
        ShoppingCart user_shopping_cart = shoppingCartService.findShoppingCartByUserId(user_id_from_session);
        // call method from ShopiingCartItemServiveImpl to create a new shopping cart
        // item
        ShoppingCartItem converted_shopping_cart_item = shoppingCartItemService
                .createShoppingCartItem(user_shopping_cart, product_item, quantity);

        // update the time to show when the shopping cart was last updated
        user_shopping_cart.setUpatedAt(user_shopping_cart.getUpatedAt());
        // save updated shopping cart into the shoppingcartrepository
        shoppingCartRepository.save(user_shopping_cart);

        // get the shopping cart items from this shopping cart
        List<ShoppingCartItem> shopping_cart_items = user_shopping_cart.getItems();
        // return the list of shopping cart items

        return new ResponseEntity<List<ShoppingCartItem>>(shopping_cart_items, HttpStatus.OK);
    }

    @DeleteMapping("/delete-item/{item_id}")
    public ResponseEntity<Void> deleteItemFromCart(@PathVariable int item_id, HttpSession session) {
        // @DeleteMapping("/clear-shopping-cart/{user_id}")
        // public ResponseEntity<Void> clearShoppingCart(@PathVariable session ) {
        // get shopping cart by user id
        // then get the list of shopping cart items
        // clear the list of shopping cart items
        // return ResponseEntity.noContent().build();
        // }

        // use findshopping cart by user id to get the shopping cart
        // then get the list of shopping cart items
        // then delete the shopping cart item by id
        // return ResponseEntity.noContent().build();
        return ResponseEntity.noContent().build();
    }
}
