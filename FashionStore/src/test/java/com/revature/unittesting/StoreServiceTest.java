package com.revature.unittesting;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dao.CustomerDao;
import com.revature.dao.StoreDao;
import com.revature.models.Customer;
import com.revature.models.LineItem;
import com.revature.models.Order;
import com.revature.models.Product;
import com.revature.models.Store;
import com.revature.services.CustomerService;
import com.revature.services.StoreService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {
	@Mock
	private StoreDao storeDao;
	private StoreService storeService;
	
	private List<Store> mockStores;
	private ArrayList<LineItem> mockLineItems;
	private ArrayList<Order> mockOrders;
		
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.storeService = new StoreService(storeDao);
		
		this.mockLineItems = new ArrayList<LineItem>();
		this.mockLineItems.add(new LineItem(new Product("T-shirt",11),10));
		this.mockLineItems.add(new LineItem(new Product("Jacket",99),10));
		
		this.mockOrders = new ArrayList<Order>();
		this.mockOrders.add(new Order(1,"win@mail.com","T-shirt","StoreA",1,11));
		this.mockOrders.add(new Order(2,"win@mail.com","T-shirt","StoreA",2,22));
		
		this.mockStores = new ArrayList<>();
		this.mockStores.add(new Store("StoreA","New York",mockLineItems,mockOrders));
	}
	
	@Test
	public void testReplenishStoreInventory() {
		Mockito.when(this.storeService.replenishStoreInventory(mockStores.get(0))).thenReturn(mockStores.get(0));
		Store replenishStoreInventory = this.storeService.replenishStoreInventory(mockStores.get(0));
		Assertions.assertEquals(replenishStoreInventory, mockStores.get(0));
	}
	
	@Test
	public void testGetAllStore() {
		Mockito.when(this.storeService.getAllStore()).thenReturn(mockStores);
		List<Store> getAllStore = this.storeService.getAllStore();
		Assertions.assertEquals(getAllStore, mockStores);
	}
	
	@Test
	public void testGetStoreByName() {
		Mockito.when(this.storeService.getAllStore()).thenReturn(mockStores);
		Store getStoreByName = this.storeService.getStoreByName("StoreA");
		Assertions.assertEquals(getStoreByName.getName(), mockStores.get(0).getName());
	}
	
	@Test
	public void testGetStoreByAddress() {
		Mockito.when(this.storeService.getAllStore()).thenReturn(mockStores);
		List<Store> getStoreByAddress = this.storeService.getStoreByAddress("New York");
		Assertions.assertEquals(getStoreByAddress.get(0).getName(), mockStores.get(0).getName());
	}
}
