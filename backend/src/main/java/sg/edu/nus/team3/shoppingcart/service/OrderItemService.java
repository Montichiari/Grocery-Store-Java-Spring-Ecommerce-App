package sg.edu.nus.team3.shoppingcart.service;

import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import java.util.List;

public interface OrderItemService {
    OrderItem getById(Long id);
    List<OrderItem> getAll();
    OrderItem create(OrderItem orderItem);
    OrderItem update(OrderItem orderItem);
    void delete(Long id);
}
