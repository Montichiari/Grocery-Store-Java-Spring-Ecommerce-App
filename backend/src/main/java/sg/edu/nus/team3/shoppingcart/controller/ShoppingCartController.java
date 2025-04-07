package sg.edu.nus.team3.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sg.edu.nus.team3.shoppingcart.service.ShoppingCartService;
import sg.edu.nus.team3.shoppingcart.serviceimpl.ShoppingCartServiceImplementation;

@Controller
public class ShoppingCartController {

	// methods 
	
	@Autowired
	private ShoppingCartService scservice; 
	
	@Autowired
	public void setShoppingCartService(ShoppingCartServiceImplementation scserviceImpl)
	{
		this.scservice = scserviceImpl; 
	}
	
	
}
