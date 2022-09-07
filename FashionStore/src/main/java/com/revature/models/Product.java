package com.revature.models;

public class Product {
	private String name;
	private float price;
	public Product(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}
}
