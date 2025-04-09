package sg.edu.nus.team3.shoppingcart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter@Setter
    private int id;
    @Getter@Setter
    private int quantity;
    @Getter@Setter
    private double unitPrice;
    @Getter@Setter
    @ManyToOne
    private Order order;
    @Getter@Setter
    @ManyToOne
    private Product product;

}
