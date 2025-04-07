package sg.edu.nus.team3.shoppingcart.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private int id;
  @Getter
  @Setter
  private String name;
  @Getter
  @Setter
  private double unitPrice;
  @Getter
  @Setter
  private int stock;
  @Getter
  @Setter
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
