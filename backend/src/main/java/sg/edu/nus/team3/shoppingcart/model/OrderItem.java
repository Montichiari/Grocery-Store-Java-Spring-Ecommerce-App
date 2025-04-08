package sg.edu.nus.team3.shoppingcart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter
    private int quantity;

    @Getter
    private double unitPrice;

    // Constructors
    public OrderItem() {
    }
}





