package com.revature.models;

public class Order {
	private int orderId;
	private String email;
	private String productName;
	private String storeName;
	private int quantity;
	private float totalPrice;
	
	public Order(int orderId, String email, String productName, String storeName,int quantity, float totalPrice) {
		super();
		this.orderId = orderId;
		this.email = email;
		this.productName = productName;
		this.storeName = storeName;
		this.quantity=quantity;
		this.totalPrice = totalPrice;
	}
	public Order(int orderId, String email, String productName, String storeName, float totalPrice) {
		super();
		this.orderId = orderId;
		this.email = email;
		this.productName = productName;
		this.storeName = storeName;
		this.totalPrice = totalPrice;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "orderId=" + orderId + ",\t email=" + email + ",\t productName=" + productName + ",\t storeName="
				+ storeName + ",\t totalPrice=" + totalPrice;
	}
}
