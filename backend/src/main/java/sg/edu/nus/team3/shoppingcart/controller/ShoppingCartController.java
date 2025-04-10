/*
 * package sg.edu.nus.team3.shoppingcart.controller;
 * 
 * import org.springframework.web.bind.annotation.RestController; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import java.util.List;
 * import org.springframework.http.HttpStatus; import
 * java.servlet.http.HttpSession;
 * 
 * import sg.edu.nus.team3.shoppingcart.model.ShoppingCart; import
 * sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem; import
 * sg.edu.nus.team3.shoppingcart.serviceimpl.ShoppingCartItemServiceImpl; import
 * sg.edu.nus.team3.shoppingcart.serviceimpl.ShoppingCartServiceImplementation;
 * import sg.edu.nus.team3.shoppingcart.service.UserService; import
 * jakarta.servlet.http.HttpSession;
 * 
 * 
 * @RestController
 * 
 * @RequestMapping("/shoppingcart") public class ShoppingCartController {
 * 
 * @Autowired private ShoppingCartServiceImplementation shoppingCartService;
 * 
 * @Autowired private ShoppingCartItemServiceImpl shoppingCartItemService;
 * 
 * @GetMapping("/items") public ResponseEntity<ShoppingCart>
 * getItemsInCart(HttpSession session) { session.getAttribute("email");
 * List<ShoppingCartItem> items =
 * shoppingCartService.findShoppingCartByUserEmail().getItems(); return new
 * ResponseEntity<>(items, HttpStatus.OK); }
 * 
 * // @PostMapping("/add-item") // public ResponseEntity<Void>
 * addItemToCart(@RequestBody Item item) { // // Logic to add an item to the
 * shopping cart // return ResponseEntity.status(HttpStatus.CREATED).build(); //
 * }
 * 
 * // @DeleteMapping("/remove-item/{itemId}") // public ResponseEntity<Void>
 * removeItemFromCart(@PathVariable String itemId) {
 * 
 * // return ResponseEntity.noContent().build(); // }
 * 
 * // @GetMapping("create-cart") // public ShoppingCart createCart() { // //
 * Logic to create a new shopping cart // return ShoppingCart; // } }
 */