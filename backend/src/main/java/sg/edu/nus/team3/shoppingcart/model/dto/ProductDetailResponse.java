package sg.edu.nus.team3.shoppingcart.model.dto;

import sg.edu.nus.team3.shoppingcart.model.Product;

public class ProductDetailResponse {
	
	private int id;
    private String name;
    private String description;
    private double unitPrice;
    private String category;
	
    public ProductDetailResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.unitPrice = product.getUnitPrice();
        this.category = product.getCategory();
    }
    
    public ProductDetailResponse() {
    	
    }
    
}
