package sg.edu.nus.team3.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.team3.shoppingcart.model.Order;


public interface OrderRepository extends JpaRepository<Order, Integer> {

}
