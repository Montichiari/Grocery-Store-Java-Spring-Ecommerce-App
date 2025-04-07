package sg.edu.nus.team3.shoppingcart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shopping_cart_items")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @ManyToOne
    private ShoppingCart shoppingCart;

    @ManyToOne
    private Product product;

    private int quantity;

    // 构造方法、Getter/Setter、toString() 等
    // ...
}