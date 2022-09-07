package com.revature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fashion.util.Logger;
import com.fashion.util.Logger.LogLevel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.StoreDao;
import com.revature.models.Customer;
import com.revature.models.LineItem;
import com.revature.models.Store;
import com.revature.services.StoreService;

public class StoreController extends HttpServlet {
	private static StoreService storeService = new StoreService(new StoreDao());
	private static ObjectMapper objmap = new ObjectMapper();
	private static Logger logger = Logger.getLogger();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/FashionStore/", "");
		resp.setContentType("application/json");
		String jsonString;
		switch (URI) {
		case "getAllStore":
			List<Store> listOfStore = storeService.getAllStore();
			logger.log(LogLevel.info, "/getAllStore");
			if (listOfStore == null) {
				jsonString = "Store not found";
			} else {
				jsonString = objmap.writeValueAsString(listOfStore);
			}
			resp.getWriter().println(jsonString);
			break;
		case "getStoreByName":
			String name = "";
			try {
				name = req.getParameter("name");
				logger.log(LogLevel.info, "/getStoreByName, request param: "+name);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Store foundStoreByName = storeService.getStoreByName(name);
			if (foundStoreByName == null) {
				jsonString = "Store not found";
			} else {
				jsonString = objmap.writeValueAsString(foundStoreByName);
			}
			resp.getWriter().println(jsonString);
			break;
		case "getStoreByAddress":
			String address = "";
			try {
				address = req.getParameter("address");
				logger.log(LogLevel.info, "/getStoreByAddress, request param: "+address);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Store> foundStoreByAddress = storeService.getStoreByAddress(address);
			if (foundStoreByAddress == null) {
				jsonString = "Store not found";
			} else {
				jsonString = objmap.writeValueAsString(foundStoreByAddress);
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
		case "replenishStoreInventory":
			String jsonString = req.getReader().lines().collect(Collectors.joining());
			Store store = objmap.readValue(jsonString, Store.class);
			store = storeService.replenishStoreInventory(store);
			jsonString = objmap.writeValueAsString(store);
			resp.setStatus(201);
			resp.getWriter().println("{ \"message\" : Successful }");
			logger.log(LogLevel.info, "/replenishStoreInventory ");

			break;
		default:
			super.doPost(req, resp);
			break;
		}
	}

}
