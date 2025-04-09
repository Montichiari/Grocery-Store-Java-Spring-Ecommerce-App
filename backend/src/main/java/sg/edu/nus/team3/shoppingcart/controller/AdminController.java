package sg.edu.nus.team3.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.serviceimpl.OrderServiceImpl;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ProductServiceImplementation;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProductServiceImplementation productService;

    // @Autowired
    // OrderServiceImpl orderService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // @GetMapping("/weekly-orders")
    // public ResponseEntity<List<Order>> getOrdersInWeek(@RequestParam String
    // param) {
    // List<Order> ordersList = orderService.getWeeklyOrders();
    // return new ResponseEntity<>(ordersList, HttpStatus.OK);
    // }

}
