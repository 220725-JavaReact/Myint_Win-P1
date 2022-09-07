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
import com.revature.models.Customer;
import com.revature.services.CustomerService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	@Mock
	private CustomerDao customerDao;
	private CustomerService customerService;
	
	private List<Customer> mockCustomers;
		
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.customerService = new CustomerService(customerDao);
		this.mockCustomers = new ArrayList<>();
		this.mockCustomers.add(new Customer("Win Thurein", "Bentonville, AR", "winthurein@mail.com"));
		this.mockCustomers.add(new Customer("Thurein", "Bentonville, AR", "thurein@mail.com"));
	}
	
	@Test
	public void testAddCustomers() {
		Customer customer = new Customer("Win Thurein", "Bentonville, AR", "winthureinmyint@mail.com");
		Mockito.when(this.customerService.addCustomer(customer)).thenReturn(customer);
		Customer addCustomer = this.customerService.addCustomer(customer);
		Assertions.assertEquals(customer, addCustomer);
	}
	
	@Test
	public void testFindAllCustomers() {
		Mockito.when(this.customerService.getAllCustomer()).thenReturn(this.mockCustomers);
		List<Customer> customers = this.customerService.getAllCustomer();
		Assertions.assertEquals(2, customers.size());
	}
	@Test
	public void testFindCustomerByEmail() {
		String email = "thurein@mail.com";
		Mockito.when(this.customerService.getAllCustomer()).thenReturn(this.mockCustomers);
		Customer customer = this.customerService.getCustomerByEmail(email);
		
		Assertions.assertEquals("Thurein", customer.getName());
	}


}
