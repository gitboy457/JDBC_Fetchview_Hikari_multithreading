package com.ace.jdbcDemo.repository;

import java.util.List;

import com.ace.jdbcDemo.entity.Customer;

public interface CustomerRepository {
	
	public boolean saveCustomer(Customer customer);
	
	public Customer getByCustId(String CustId);
	
	public Customer getpersonInfoView();
	
	public List<Customer> AllCustomer();
	
	
	
	

}
