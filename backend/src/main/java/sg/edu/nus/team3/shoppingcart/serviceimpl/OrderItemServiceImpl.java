package sg.edu.nus.team3.shoppingcart.serviceimpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.team3.shoppingcart.model.OrderItem;
import sg.edu.nus.team3.shoppingcart.repository.OrderItemRepository;
import sg.edu.nus.team3.shoppingcart.service.OrderItemService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem getById(Long id) {
        return new OrderItem();
    }

    @Override
    public List<OrderItem> getAll() {
        return new ArrayList<OrderItem>();
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return new OrderItem();
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return new OrderItem();
    }

    @Override
    public void delete(Long id) {

    }
}
