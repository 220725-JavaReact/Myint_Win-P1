package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fashion.util.ConnectionUtil;
import com.revature.models.Customer;
import com.revature.models.Order;

public class CustomerDao implements Dao<Customer> {

	@Override
	public Customer addInstance(Customer instance) {
		String sql = "insert into customer values(?, ?, ?) returning email";
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, instance.getEmail());
			stmt.setString(2, instance.getName());
			stmt.setString(3, instance.getAddress());
			stmt.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return instance;
	}

	@Override
	public List<Customer> getAllInstance() {
		String sql = "select * from customer";
		List<Customer> listOfCustomer = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				sql = "select co.order_id,co.customer_email,co.product_name,co.store_name,co.total_price  from customer_order co where co.customer_email = ?";
				PreparedStatement prepStm = con.prepareStatement(sql);
				prepStm.setString(1, rs.getString("email"));
				ResultSet orders = prepStm.executeQuery();
				ArrayList<Order> listOfOrder = new ArrayList<>();
				while (orders.next()) {
					listOfOrder.add(new Order(orders.getInt(1), orders.getString(2), orders.getString(3),
							orders.getString(4), orders.getFloat(5)));
				}
				listOfCustomer.add(new Customer(rs.getString("name"), rs.getString("address"), rs.getString("email"),
						listOfOrder));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return listOfCustomer;
	}

	@Override
	public Customer updateInstance(Customer instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteInstance(Customer instance) {
		// TODO Auto-generated method stub
		return null;
	}

}
