package com.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Customer;

@Repository
public class CustomerDAOimpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Customer> getCustomers() {

	// get the current hibernate session
	Session session = sessionFactory.getCurrentSession();

	// create a query
	Query<Customer> customerQuery = session.createQuery("from Customer order by lastName", Customer.class);

	// execute query and get the result list
	List<Customer> customers = customerQuery.getResultList();

	return customers;
    }

    @Override
    @Transactional
    public void saveCustomer(Customer theCustomer) {

	Session session = sessionFactory.getCurrentSession();

	// saving or updating the customer to the database
	session.saveOrUpdate(theCustomer);

    }

    @Override
    @Transactional
    public Customer getCustomer(int theId) {

	Session session = sessionFactory.getCurrentSession();

	return session.get(Customer.class, theId);
    }

    @Override
    @Transactional
    public void deleteCustomer(int theId) {

	Session session = sessionFactory.getCurrentSession();

	Query<?> query = session.createQuery("delete from Customer where id = :customerId");

	// we set parameter for the query and than executeUpdate
	query.setParameter("customerId", theId).executeUpdate();

    }

    @Override
    @Transactional
    public List<Customer> searchCustomers(String theSearchName) {

	Session session = sessionFactory.getCurrentSession();

	Query<Customer> query;

	if (theSearchName != null && theSearchName.trim().length() > 0) {

	    query = session.createQuery("from Customer where lower(firstName) like :searchName"
					+ " or lower(lastName) like :searchName order by lastName", Customer.class);

	    query.setParameter("searchName", "%" + theSearchName.toLowerCase() + "%").getResultList();

	} else {

	    query = session.createQuery("from Customer order by lastName", Customer.class);

	}

	return query.getResultList();
    }

}
