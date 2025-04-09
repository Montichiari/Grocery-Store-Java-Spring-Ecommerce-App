package sg.edu.nus.team3.shoppingcart.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.team3.shoppingcart.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.createAt BETWEEN :startDate AND :endDate")
    public List<Order> findWeeklyOrders(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
