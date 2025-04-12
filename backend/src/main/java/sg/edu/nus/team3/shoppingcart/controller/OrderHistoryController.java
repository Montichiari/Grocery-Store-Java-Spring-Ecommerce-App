package sg.edu.nus.team3.shoppingcart.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.model.User;
import sg.edu.nus.team3.shoppingcart.serviceimpl.OrderServiceImpl;
import sg.edu.nus.team3.shoppingcart.serviceimpl.UserServiceImpl;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ShoppingCartItemServiceImpl;

@Controller
@RequestMapping("/orders")
public class OrderHistoryController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserServiceImpl userService;

    // Web endpoint for viewing order history (Thymeleaf)
    @GetMapping
    public String viewMyOrders(
            Model model,
            Principal principal,
            @RequestParam(defaultValue = "0") int page) {

        if (principal == null) {
            // Redirect to login page if user is not logged in
            return "redirect:/login";
        }

        String username = principal.getName();
        User user = userService.findByUsername(username);

        Page<Order> orderPage = orderService.getOrdersByUser(user, PageRequest.of(page, 10));
        List<Order> orders = orderPage.getContent();

        if (orders.isEmpty()) {
            model.addAttribute("message", "You have no previous orders.");
        }

        model.addAttribute("orders", orders);
        model.addAttribute("totalPages", orderPage.getTotalPages());
        model.addAttribute("currentPage", page);

        return "order-history"; // Thymeleaf view name
    }

    // Optional: REST API for React or external frontends
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<?> getOrderHistoryApi(
            Principal principal,
            @RequestParam(defaultValue = "0") int page) {

        if (principal == null) {
            return ResponseEntity.status(401).body("Not logged in.");
        }

        String username = principal.getName();
        User user = userService.findByUsername(username);

        Page<Order> orderPage = orderService.getOrdersByUser(user, PageRequest.of(page, 10));
        return ResponseEntity.ok(orderPage);
    }
}
