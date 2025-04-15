package sg.edu.nus.team3.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.service.ProductService;

import java.util.List;

/**
 * @author Gao Shengyi
 */

@RestController
@RequestMapping("/products")
public class BrowseController {
    @Autowired
    private ProductService productService;
    public BrowseController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> browseAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam int id) {
        return productService.searchProductById(id);
    }

    @GetMapping("/filter")
    public List<Product> filterByPrice(
            @RequestParam Double min,
            @RequestParam Double max) {
        return productService.getProductsByPriceRange(min, max);
    }
}


