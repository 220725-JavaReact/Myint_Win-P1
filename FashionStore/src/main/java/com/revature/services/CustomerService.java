package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.dao.Dao;
import com.revature.models.Customer;

public class CustomerService {
	private Dao<Customer> customerDao;

    public CustomerService(Dao<Customer> customerDao) {
        this.customerDao = customerDao;
    }
    public Customer addCustomer(Customer customer){
        return customerDao.addInstance(customer);
    }
    public List<Customer> getAllCustomer(){
        return customerDao.getAllInstance();
    }
    public Customer getCustomerByEmail(String email){
        List<Customer> listOfCustomer = getAllCustomer(); 

        Optional<Customer> foundCustomer= listOfCustomer.stream()
            .filter(customer -> customer.getEmail().equals(email))
            .findFirst();
            
        if (foundCustomer.isPresent()) {
            return foundCustomer.get();

        } else {
        	Customer noCustomer = new Customer();
        	noCustomer.setName("No customer was found");
            return null;
        }
    }
}
