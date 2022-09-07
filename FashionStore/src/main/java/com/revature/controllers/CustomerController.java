package com.revature.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fashion.util.Logger;
import com.fashion.util.Logger.LogLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.CustomerDao;
import com.revature.models.Customer;
import com.revature.services.CustomerService;

public class CustomerController extends HttpServlet {
	private static CustomerService customerService = new CustomerService(new CustomerDao());
	private static ObjectMapper objmap = new ObjectMapper();
	private static Logger logger = Logger.getLogger();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/FashionStore/", "");
		resp.setContentType("application/json");
		String jsonString;
		switch (URI) {
		case "searchCustomer":
			String email = "";

			try {
				email = req.getParameter("email");
				logger.log(LogLevel.info, "/searchCustomer, urlParam: "+email);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			Customer foundCustomer = customerService.getCustomerByEmail(email);
			if (foundCustomer == null) {
				logger.log(LogLevel.error, "/searchCustomer, urlParam: "+email +" not found.");
				jsonString = "Customer not found";
			} else {
				jsonString = objmap.writeValueAsString(foundCustomer);
			}

			resp.getWriter().println(jsonString);

			break;
		case "allCustomer":
			List<Customer> listOfCustomer = customerService.getAllCustomer();
			logger.log(LogLevel.info, "/allCustomer");
			if (listOfCustomer == null) {
				jsonString = "Customer not found";
			} else {
				jsonString = objmap.writeValueAsString(listOfCustomer);
			}

			resp.getWriter().println(jsonString);

			break;
		default:
			super.doGet(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/FashionStore/", "");

		resp.setContentType("application/json");

		switch (URI) {
		case "registerCustomer":
			String jsonString = req.getReader().lines().collect(Collectors.joining());
			Customer customer = objmap.readValue(jsonString, Customer.class);
			customer = customerService.addCustomer(customer);
			logger.log(LogLevel.info, "/registerCustomer, "+ "customer email: "+customer.getEmail());
			jsonString = objmap.writeValueAsString(customer);
			resp.getWriter().println(jsonString);
			resp.setStatus(201);
			break;
		default:
			super.doPost(req, resp);
			break;
		}
	}
}
