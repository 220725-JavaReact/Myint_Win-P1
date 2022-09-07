package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fashion.util.ConnectionUtil;
import com.revature.models.Customer;
import com.revature.models.LineItem;
import com.revature.models.Order;
import com.revature.models.Product;
import com.revature.models.Store;

public class StoreDao implements Dao<Store>{

	@Override
	public Store addInstance(Store instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> getAllInstance() {
		String sql = "select * from store_front";
		List<Store> listOfStore = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				//get Line item from store
				sql = "select li.product_name,p.price,li.store_name,li.quantity  from line_item li join product p on li.product_name = p.name where li.store_name = ?";
				PreparedStatement prepStm = con.prepareStatement(sql);
				prepStm.setString(1, rs.getString("name"));
				ResultSet lineItems = prepStm.executeQuery();
				ArrayList<LineItem> listOfLineItem = new ArrayList<>();
				while (lineItems.next()) {
					listOfLineItem.add(new LineItem(new Product(lineItems.getString(1), lineItems.getFloat(2)), lineItems.getInt(4)));
				}
				
				//get order from store
				sql = "select * from customer_order co join product p on co.product_name = p.name where co.store_name = ?";
				prepStm = con.prepareStatement(sql);
				prepStm.setString(1, rs.getString("name"));
				ResultSet orders = prepStm.executeQuery();
				ArrayList<Order> listOfOrder = new ArrayList<>();
				while (orders.next()) {
					listOfOrder.add(new Order(orders.getInt(1), orders.getString(2), orders.getString(3),orders.getString(4), orders.getFloat(5)));
				}
				listOfStore.add(new Store(rs.getString(1),rs.getString(2),listOfLineItem,listOfOrder));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return listOfStore;
	}

	@Override
	public Store updateInstance(Store instance) {
		String sql = "select store_inventory_replenish(?,?,?);";
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(2, instance.getName());
			ArrayList<LineItem> listOfLineItem = instance.getProduct();
			for (LineItem item:listOfLineItem) {
				stmt.setString(1, item.getProduct().getName());
				stmt.setInt(3, item.getQuantity());
				stmt.executeQuery();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return instance;
	}

	@Override
	public Store deleteInstance(Store instance) {
		// TODO Auto-generated method stub
		return null;
	}

}
