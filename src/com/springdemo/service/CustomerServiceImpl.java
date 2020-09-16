package com.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdemo.dao.CustomerDAO;
import com.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

    // need to inject customer DAO
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public List<Customer> getCustomers() {
	return customerDAO.getCustomers();
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
	customerDAO.saveCustomer(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {

	return customerDAO.getCustomer(theId);
    }

    @Override
    public void deleteCustomer(int theId) {
	customerDAO.deleteCustomer(theId);
    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {
	
	return customerDAO.searchCustomers(theSearchName);
    }

}
