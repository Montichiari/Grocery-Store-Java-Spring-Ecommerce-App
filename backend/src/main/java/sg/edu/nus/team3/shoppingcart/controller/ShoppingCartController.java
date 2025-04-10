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
        List<ShoppingCartItem> list_of_items = shoppingCartService.findShoppingCartByUserId(userId).getItems();
        return new ResponseEntity<List<ShoppingCartItem>>(list_of_items, HttpStatus.OK);
    }

    @PostMapping("/add-item/{product_id}")
    public ResponseEntity<List<ShoppingCartItem>> addItemToCart(@PathVariable Product product_item, int quantity,
            HttpSession session) {
        // do if else statement here if the user id is null/not valid , then reject the
        // user and return error message
        // else continue with the flow
        // get user id from session object
        if (session.getAttribute("id") == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        int user_id_from_session = (int) session.getAttribute("id");
        // find shopping cart by user Id
        ShoppingCart user_shopping_cart = shoppingCartService.findShoppingCartByUserId(user_id_from_session);
        // call method from ShopiingCartItemServiveImpl to create a new shopping cart
        // item
        ShoppingCartItem converted_shopping_cart_item = shoppingCartItemService
                .createShoppingCartItem(user_shopping_cart, product_item, quantity);
        // once get converted shopping cart item, add this shopping cart item to the
        // shopping cart
        user_shopping_cart.getItems().add(converted_shopping_cart_item);
        // update the time to show when the shopping cart was last updated
        user_shopping_cart.setUpatedAt(user_shopping_cart.getUpatedAt());
        // save updated shopping cart into the shoppingcartrepository
        shoppingCartRepository.save(user_shopping_cart);
        // get the shopping cart items from this shopping cart
        List<ShoppingCartItem> shopping_cart_items = user_shopping_cart.getItems();
        // return the list of shopping cart items with the new item added to the cart
        return new ResponseEntity<List<ShoppingCartItem>>(shopping_cart_items, HttpStatus.OK);
    }

    // need to continue working on this method
    @DeleteMapping("/delete-item/")
    public ResponseEntity<Void> deleteAllItemsInCart(HttpSession session) {
        // get user id from session object
        // find user shopping cart from user id
        // get shopping cart list of items and delete all items
        // delete these items from the shopping cart item database also
        session.getAttribute("id");
        if (session.getAttribute("id") == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        int user_id_from_session = (int) session.getAttribute("id");
        // find shopping cart by user id
        ShoppingCart user_shopping_cart = shoppingCartService.findShoppingCartByUserId(user_id_from_session);
        // get list of shopping cart items
        List<ShoppingCartItem> list_of_shopping_cart_items = user_shopping_cart.getItems();

        // delete all items in the cart from the database
        for (int i = 0; i < list_of_shopping_cart_items.size(); i++) {
            // get each shopping cart item id and delete from the database
            ShoppingCartItem shopping_cart_item = list_of_shopping_cart_items.get(i);
            // after getting each shopping cart item, delete each item from the database of
            // shopping cart item
            int shopping_cart_item_id = shopping_cart_item.getId();
            shoppingCartItemRepository.deleteById(shopping_cart_item_id);
        }

        // clear memory of the list after removing from database
        list_of_shopping_cart_items.removeAll(list_of_shopping_cart_items);

        return ResponseEntity.noContent().build();

    }

}
