package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.dao.Dao;
import com.revature.models.Customer;
import com.revature.models.Order;

public class OrderService {
	private Dao<Order> orderDao;

    public OrderService(Dao<Order> orderDao) {
        this.orderDao = orderDao;
    }
    public Order addOrder(Order instance){
        return orderDao.addInstance(instance);
    }

}
