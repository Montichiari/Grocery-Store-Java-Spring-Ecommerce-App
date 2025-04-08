package sg.edu.nus.team3.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
