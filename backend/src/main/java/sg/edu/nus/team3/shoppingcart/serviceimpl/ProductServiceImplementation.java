package sg.edu.nus.team3.shoppingcart.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.Product;
import sg.edu.nus.team3.shoppingcart.service.ProductService;

@Service
@Transactional
public class ProductServiceImplementation implements ProductService{

  @Autowired
  public Product productRepo; 

}
