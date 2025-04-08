package sg.edu.nus.team3.shoppingcart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// @author Jared Chua
@Entity
@Table(name = "product")
@RequiredArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private int id;
  @Getter
  @Setter
  @NotNull(message = "Name cannot be null")
  private String name;
  @Getter
  @Setter
  @Positive(message = "Unit price of a product cannot be negative")
  @Column(name = "unit_price")
  private double unitPrice;
  @Getter
  @Setter
  @Positive(message = "Number of stock of a product cannot be negative")
  private int stock;
  @Getter
  @Setter
  // TODO: Add custom ENUM validator here
  private String category;

  public Product(int id, String name, double unitPrice, int stock, String category) {
    this.name = name;
    this.unitPrice = unitPrice;
    this.stock = stock;
    this.category = category;
  }

  @Override
  public boolean equals(Object comparator) {
    if (!(comparator instanceof Product))
      return false;
    Product that = (Product) comparator;

    return this.id == that.id && this.name == that.name && this.unitPrice == that.unitPrice && this.stock == that.stock
        && this.category == that.category;
  }
}
