package sg.edu.nus.team3.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> browseAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String id) {
        return productService.searchProducts(id);
    }

    @GetMapping("/filter")
    public List<Product> filterByPrice(
            @RequestParam Double min,
            @RequestParam Double max) {
        return productService.getProductsByPriceRange(min, max);
    }
}


