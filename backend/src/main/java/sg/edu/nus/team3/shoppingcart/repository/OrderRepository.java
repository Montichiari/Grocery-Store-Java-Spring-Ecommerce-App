package sg.edu.nus.team3.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.team3.shoppingcart.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // @Query("SELECT o FROM Order o")
    // public List<Order> findWeeklyOrders();
}
