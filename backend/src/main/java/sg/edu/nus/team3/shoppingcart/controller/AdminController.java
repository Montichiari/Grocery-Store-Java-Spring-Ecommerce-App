package sg.edu.nus.team3.shoppingcart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.projections.OrderProjection;
import sg.edu.nus.team3.shoppingcart.serviceimpl.OrderServiceImpl;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ProductServiceImplementation;

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
    public ResponseEntity<List<OrderProjection>> getOrdersInWeek() {
        List<OrderProjection> ordersList = orderService.getWeeklyOrders();
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Map<String, Object> product) {
        String name = product.get("name").toString();
        double unitPrice = (double) product.get("unitPrice");
        int stock = (int) product.get("stock");
        String category = product.get("category").toString();

        Product productToSave = new Product(name, unitPrice, stock, category);
        productService.createProduct(productToSave);
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

    // ! Delete not allowed, since there are old Orders tied to the products
    // ! Consider performing a cascade action on affected orders to a placeholder
    // ! product (e.g. "Product not available") before deletion
    // @DeleteMapping("/product/{id}")
    // public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
    // productService.deleteProductById(id);
    // return new ResponseEntity<>(HttpStatus.OK);
    // }
}
