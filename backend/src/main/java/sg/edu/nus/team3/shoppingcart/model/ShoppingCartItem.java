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

   
    // shopping cart items is the owning side 
    @ManyToOne
    private ShoppingCart shoppingCart;

    @ManyToOne
    private Product product;

    private int quantity;

    public void addItemToCart(ShoppingCart cart, Product product, int quantity) {
        this.shoppingCart = cart;
        this.product = product;
        this.quantity = quantity;
        // 这里可能有额外的业务逻辑，如库存检查等
    }

    public ShoppingCartItem viewItem() {
        return this;
        // 或者返回一个包含必要信息的DTO
    }
}