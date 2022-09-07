package com.revature.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fashion.util.Logger;
import com.fashion.util.Logger.LogLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.OrderDao;
import com.revature.models.Order;
import com.revature.services.OrderService;

public class OrderController extends HttpServlet {
	private static OrderService orderService = new OrderService(new OrderDao());
	private static ObjectMapper objmap = new ObjectMapper();
	private static Logger logger = Logger.getLogger();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/FashionStore/", "");

		resp.setContentType("application/json");

		switch (URI) {
		case "order":
			String jsonString = req.getReader().lines().collect(Collectors.joining());
			Order order = objmap.readValue(jsonString, Order.class);
			order = orderService.addOrder(order);

			if (order.getOrderId() > 0) {
				resp.setStatus(201);
				resp.getWriter().println("{ \"orderID\" : " + order.getOrderId() + "}");
				logger.log(LogLevel.info, "/order, " + "orderID: " + order.getOrderId());
			} else {
				resp.setStatus(404);
				resp.getWriter().println("{ \"message\" : Item out of stock }");
				logger.log(LogLevel.info, "/order, " + order.getProductName() +" out of stock" );
			}

			break;
		default:
			super.doPost(req, resp);
			break;
		}
	}
}
