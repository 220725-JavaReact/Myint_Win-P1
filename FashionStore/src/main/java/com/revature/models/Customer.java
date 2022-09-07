package com.revature.models;

import java.util.ArrayList;

public class Customer {
	private String name;
	private String address;
	private String email;
	private ArrayList<Order> order;
	public Customer(String name, String address, String email, ArrayList<Order> order) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.order = order;
	}
	public Customer(String name, String address, String email) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Order> getOrder() {
		return order;
	}
	public void setOrder(ArrayList<Order> order) {
		this.order = order;
	}
	
}