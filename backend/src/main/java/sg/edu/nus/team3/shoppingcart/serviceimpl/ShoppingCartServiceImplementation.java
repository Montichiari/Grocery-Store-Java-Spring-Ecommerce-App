package sg.edu.nus.team3.shoppingcart.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCart;
import sg.edu.nus.team3.shoppingcart.model.User;

import sg.edu.nus.team3.shoppingcart.service.Product;
import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;

@Service
@Transactional
public class ShoppingCartServiceImplementation implements ShoppingCartService {

	@Autowired
	ShoppingCart screpo; 
	
	@Override
	@Transactional
	public void addProductToCart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void updateCartQuantity() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	@Transactional
	public void deleteCartQuantity() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	//@Transactional
	//public ArrayList<Product> viewCart() {
		// TODO Auto-generated method stub
	//	return null;
	//}


}
