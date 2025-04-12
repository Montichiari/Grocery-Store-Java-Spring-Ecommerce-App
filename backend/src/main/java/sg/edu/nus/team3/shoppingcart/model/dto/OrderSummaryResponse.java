package sg.edu.nus.team3.shoppingcart.model.dto;

import java.time.LocalDateTime;

import sg.edu.nus.team3.shoppingcart.model.Order;

public class OrderSummaryResponse {
	private int orderId;
	private LocalDateTime createAt;
	private double totalAmount;
	private String status;

	public OrderSummaryResponse(Order order) {
		this.orderId = order.getId();
		this.createAt = order.getCreateAt();
		this.totalAmount = order.getTotalAmount();
		this.status = order.getStatus();
	}
	
	public OrderSummaryResponse() {
		
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
