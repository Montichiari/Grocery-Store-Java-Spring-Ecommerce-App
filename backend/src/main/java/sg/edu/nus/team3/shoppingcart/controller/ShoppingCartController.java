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
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartItemService;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;

// @Authored by @thina
@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCart_service;

    @Autowired
    private ShoppingCartItemService shoppingCartItem_service;

    // need to test
    @GetMapping("/items")
    public ResponseEntity<List<ShoppingCartItem>> getItemsInCart(HttpSession session) {
        int shoppingCartId = (int) session.getAttribute("cartId");
        List<ShoppingCartItem> list_items = shoppingCart_service.findShoppingCartByUserId(userId).getItems();
        return new ResponseEntity<List<ShoppingCartItem>>(list_items, HttpStatus.OK);
    }

    // need to test
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
        int user_id_session = (int) session.getAttribute("id");
        // find shopping cart by user Id
        ShoppingCart userShoppingCart = shoppingCart_service.findShoppingCartByUserId(user_id_session);
        // call method from ShoppingCartService to create a new shopping cart
        // item
        ShoppingCartItem newCartItem = shoppingCartItem_service.createShoppingCartItem(userShoppingCart, product_item,
                quantity);
        // once get converted shopping cart item, add this shopping cart item to the
        // cart

        // create addShoppingCartItem method in the service impl class
        userShoppingCart.getItems().add(newCartItem);
        // update the time to show when the shopping cart was last updated
        userShoppingCart.setUpatedAt(userShoppingCart.getUpatedAt());
        // save updated shopping cart into the shoppingcartrepository
        shoppingCart_service.saveShoppingCart(userShoppingCart);
        // get the updated list of items from this shopping cart
        List<ShoppingCartItem> list_CartItems = userShoppingCart.getItems();
        // return the list of shopping cart items with the new item added to the cart
        return new ResponseEntity<List<ShoppingCartItem>>(list_CartItems, HttpStatus.OK);
    }

    // need to test
    @DeleteMapping("/delete-item/")
    public ResponseEntity<Void> clearShoppingCart(HttpSession session) {

        if (session.getAttribute("id") == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        // user id from session object
        int userid_session = (int) session.getAttribute("id");

        // call method from service class to delete all items in cart
        shoppingCart_service.deleteAllItemsInCart(userid_session);

        // the following code has been implemented into the service method
        // deleteAllItemsInCart
        // // find shopping cart by user id
        // ShoppingCart user_shopping_cart =
        // shoppingCart_service.findShoppingCartByUserId(userid_session);
        // // get list of shopping cart items
        // List<ShoppingCartItem> shoppingcart_items = user_shopping_cart.getItems();

        // // delete all items in the cart from the database
        // for (int i = 0; i < shoppingcart_items.size(); i++) {
        // // get each shopping cart item id and delete from the database
        // ShoppingCartItem shoppingcart_item = shoppingcart_items.get(i);
        // // after getting each shopping cart item, delete each item from the database
        // of
        // // shopping cart item
        // int shopping_cart_item_id = shoppingcart_item.getId();
        // sc_itemrepo.deleteById(shopping_cart_item_id);
        // }

        // clear memory of the list after removing from database
        // shoppingcart_items.removeAll(shoppingcart_items);

        return ResponseEntity.noContent().build();

    }

}
