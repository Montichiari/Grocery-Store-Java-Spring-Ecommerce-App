package sg.edu.nus.team3.shoppingcart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shopping_cart_items")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @ManyToOne
    @JoinColumn(name= "shopping_cart_id")
    @Getter @Setter
    private ShoppingCart shoppingCart;

    @ManyToOne
    @Getter @Setter
    private Product product;

    @Getter @Setter
    private int quantity;

    public void addItemToCart(ShoppingCart cart, Product product, int quantity) {
        if (cart == null || product == null) {
            throw new IllegalArgumentException("购物车和商品不能为null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("数量必须大于0");
        }
        this.shoppingCart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public ShoppingCartItem viewItem() {
        return this;
    }

	public Object getShoppingCart() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getQuantity() {
		// TODO Auto-generated method stub
		return null;
	}
}