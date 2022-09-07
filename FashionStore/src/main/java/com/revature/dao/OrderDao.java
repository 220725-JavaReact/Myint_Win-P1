package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.fashion.util.ConnectionUtil;
import com.revature.models.Order;

public class OrderDao implements Dao<Order>{

	@Override
	public Order addInstance(Order instance) {
		String sql = "select order_product(?,?,?,?);";
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, instance.getEmail());
			stmt.setString(2, instance.getProductName());
			stmt.setString(3, instance.getStoreName());
			stmt.setInt(4, instance.getQuantity());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            instance.setOrderId(rs.getInt(1));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return instance;
	}

	@Override
	public List<Order> getAllInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order updateInstance(Order instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order deleteInstance(Order instance) {
		// TODO Auto-generated method stub
		return null;
	}

}
