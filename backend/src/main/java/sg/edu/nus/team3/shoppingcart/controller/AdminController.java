package sg.edu.nus.team3.shoppingcart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.serviceimpl.OrderServiceImpl;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ProductServiceImplementation;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

// @author: Jared Chua
// TODO: Add validation rules for incorrect/empty parameters, with the appropriate http response
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProductServiceImplementation productService;

    @Autowired
    OrderServiceImpl orderService;

    @GetMapping("/product-list")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/weekly-orders")
    public ResponseEntity<List<Order>> getOrdersInWeek(@RequestParam String param) {
        List<Order> ordersList = orderService.getWeeklyOrders();
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody String entity) {
        // TODO: process POST request
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Map<String, Object> product) {
        String editedName = product.get("name").toString();
        double editedUnitPrice = (double) product.get("unitPrice");
        int editedStock = (int) product.get("stock");
        String editedCategory = product.get("category").toString();

        Product editedProduct = new Product(editedName, editedUnitPrice, editedStock, editedCategory);
        productService.editProductById(id, editedProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
