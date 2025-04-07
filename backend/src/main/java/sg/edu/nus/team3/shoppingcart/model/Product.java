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

}
