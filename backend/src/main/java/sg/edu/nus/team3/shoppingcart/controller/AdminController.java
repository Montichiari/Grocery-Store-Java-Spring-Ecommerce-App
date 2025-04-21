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

import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.projections.OrderProjection;
import sg.edu.nus.team3.shoppingcart.serviceimpl.OrderServiceImpl;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ProductServiceImplementation;
import sg.edu.nus.team3.shoppingcart.util.APIResponse;

import org.springframework.web.bind.annotation.PostMapping;

// @author: Jared Chua
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
    public ResponseEntity<List<OrderProjection>> getOrdersInWeek() {
        List<OrderProjection> ordersList = orderService.getWeeklyOrders();
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody Map<String, Object> product) {
        APIResponse response = new APIResponse();

        // Extract and typecast body JSON for processing
        String name = product.get("name").toString();
        double unitPrice = Double.parseDouble((String) product.get("unitPrice"));
        int stock = (int) product.get("stock");
        String category = product.get("category").toString();
        String image = product.get("image").toString();
        // Temporarily instantiate new Product object to be saved
        Product productToSave = new Product(name, unitPrice, stock, category, image);
        // Create the product
        productService.createProduct(productToSave);

        response.setMessage("Product has been successfully created");
        return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Map<String, Object> product) {
        // Extract and typecast body JSON for processing
        String editedName = product.get("name").toString();
        double editedUnitPrice = Double.parseDouble((String) product.get("unitPrice"));
        int editedStock = (int) product.get("stock");
        String editedCategory = product.get("category").toString();
        String editedImage = product.get("image").toString();

        // Temporarily instantiate new Product object to be saved
        Product editedProduct = new Product(editedName, editedUnitPrice, editedStock, editedCategory, editedImage);
        // Edit the product
        productService.editProductById(id, editedProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
