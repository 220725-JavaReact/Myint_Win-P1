package com.revature.models;

import java.util.ArrayList;

public class Store {
	private String name;
	private String address;
	private ArrayList<LineItem> product;
	private ArrayList<Order> order;
	public Store(String name, ArrayList<LineItem> lineItem) {
		this.name = name;
		this.product = lineItem;
	}
	public Store(String name, String address, ArrayList<LineItem> product, ArrayList<Order> order) {
		super();
		this.name = name;
		this.address = address;
		this.product = product;
		this.order = order;
	}
	public Store(String name, String address) {
		super();
		this.name = name;
		this.address = address;
		this.product = new ArrayList<LineItem>();
		this.order = new ArrayList<Order>();
	}
	public Store() {
		this.product = new ArrayList<LineItem>();
		this.order = new ArrayList<Order>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<LineItem> getProduct() {
		return product;
	}
	public void setProduct(ArrayList<LineItem> product) {
		this.product = product;
	}
	public void addProduct(LineItem product) {
		this.product.add(product);
	}
	public ArrayList<Order> getOrder() {
		return order;
	}
	public void setOrder(ArrayList<Order> order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "StoreFront [name=" + name + ", address=" + address + ", product=" + product + ", order=" + order + "]";
	}
	
	
}