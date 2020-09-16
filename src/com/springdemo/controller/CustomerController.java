package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    // need to inject the customer service
    @Autowired
    private CustomerService customerService;

    // @RequestMapping(value = "/list", method = RequestMethod.GET)
    @GetMapping(value = "/list")
    public String listCustomers(Model model) {

	// get customers from the service
	List<Customer> customers = customerService.getCustomers();

	// customers.sort((it1, it2) -> {
	// return it1.getLastName().compareTo(it2.getLastName());
	// });

	model.addAttribute("customers", customers);

	return "list-customers";
    }

    @GetMapping(value = "/showFormForAdd")
    public String showFormForAdd(Model model) {

	Customer theCustomer = new Customer();
	model.addAttribute("customer", theCustomer);

	return "customer-form";
    }

    @PostMapping(value = "/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

	// save the customer using our service
	customerService.saveCustomer(theCustomer);

	return "redirect:/customer/list";
    }

    @GetMapping(value = "/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model model) {

	// get the customer from our service
	Customer theCustomer = customerService.getCustomer(theId);

	// set customer as a model attribute to pre-populate the form
	// we add the same name as we had before, <form:form action="saveCustomer" modelAttribute="customer"
	// method="POST">
	model.addAttribute("customer", theCustomer);

	// send over to our form
	return "customer-form";
    }

    @GetMapping(value = "/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {

	customerService.deleteCustomer(theId);

	return "redirect:/customer/list";
    }
    
    @RequestMapping(value = "search")
    public String search(@RequestParam("theSearchName") String theSearchName, Model model) {
	
	List<Customer> customers = customerService.searchCustomers(theSearchName);
	
	model.addAttribute("customers", customers);
	
	return "list-customers";
    }

}
