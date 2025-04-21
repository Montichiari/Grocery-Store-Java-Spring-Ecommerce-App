package sg.edu.nus.team3.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sg.edu.nus.team3.shoppingcart.model.Order;
import sg.edu.nus.team3.shoppingcart.projections.OrderProjection;
import sg.edu.nus.team3.shoppingcart.serviceimpl.OrderServiceImpl;
import sg.edu.nus.team3.shoppingcart.util.DateUtil;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    // @author: Jared Chua
    @Test
    @DisplayName("Should return all orders in database")
    public void retrieveAllOrdersTest() {
        List<Order> allOrdersList = orderServiceImpl.getAllOrders();
        assertTrue(allOrdersList.size() > 0, "There should be more than 0 orders in the orders table");
    }

    // @author: Jared Chua
    @Test
    @DisplayName("Should return one order by their id")
    public void retrieveOrderByIdTest() {
        int orderIdToRetrieve = 1;
        Optional<Order> orderToRetrieve = orderServiceImpl.getOptOrderById(orderIdToRetrieve);

        assertTrue(orderToRetrieve.isPresent(), "There should be at least 1 order retrieved");
    }

    // @author: Jared Chua
    @Test
    @DisplayName("Should return all the orders in the previous week")
    public void retrieveOrdersInCurrentWeekTest() {
        Date today = new Date();
        Date weekBefore = DateUtil.subtractDays(today, 7);
        List<OrderProjection> weeklyOrdersList = orderServiceImpl.getWeeklyOrders();

        assertTrue(weeklyOrdersList.size() > 0, "There should be at least 1 order within the week");

        for (OrderProjection order : weeklyOrdersList) {
            Date orderDate = DateUtil.convertToDate(order.getCreateAt());
            boolean isWithinWeek = DateUtil.isDateBetween(weekBefore, today, orderDate);
            assertTrue(isWithinWeek, "All orders should be within 7 days");
        }
    }

}
