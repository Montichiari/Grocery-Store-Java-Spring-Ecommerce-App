package sg.edu.nus.team3.shoppingcart.model.dto;

public class AddToCartRequest {
	
	 private int productId;
	 private int quantity;
	 
	 
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	 
	public AddToCartRequest() {
		
	}
}
