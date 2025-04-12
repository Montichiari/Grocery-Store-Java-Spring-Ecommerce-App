package sg.edu.nus.team3.shoppingcart.model.dto;

import sg.edu.nus.team3.shoppingcart.model.Product;

public class ProductResponse {
	
    private int id;
    private String name;
    private double unitPrice;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.unitPrice = product.getUnitPrice();
    }
    
    public ProductResponse() {
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
    
    
}
