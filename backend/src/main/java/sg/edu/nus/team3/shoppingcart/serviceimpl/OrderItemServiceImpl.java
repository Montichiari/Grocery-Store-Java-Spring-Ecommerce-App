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
        // return orderItemRepository.findById(id).orElseThrow(() -> new
        // RuntimeException("OrderItem not found"));
    }

    @Override
    public List<OrderItem> getAll() {
        return new ArrayList<OrderItem>();
        // return orderItemRepository.findAll();
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return new OrderItem();
        // return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return new OrderItem();
        // if (!orderItemRepository.existsById((long) orderItem.getId())) {
        // throw new RuntimeException("OrderItem not found");
        // }
        // return orderItemRepository.save(orderItem);
    }

    @Override
    public void delete(Long id) {

        // orderItemRepository.deleteById(id);
    }
}
