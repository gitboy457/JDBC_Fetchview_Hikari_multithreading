package com.ace.jdbcDemo.app.thread;

import java.sql.Date;

import com.ace.jdbcDemo.app.Application;
import com.ace.jdbcDemo.entity.Customer;

public class SaveRecordThread implements Runnable{

	@Override
	public void run() {
		
		  Customer customer= new Customer(); customer.setCustId("aBcd");
		  customer.setCreateDate(new Date(new java.util.Date().getTime()));
		  customer.setCust_name("ashif"); customer.setEmail("ashif123@gmail.com");
		  customer.setMobile("8765432356");
		  
		  if (Application.customerRepository.saveCustomer(customer)) {
		  System.out.println("customer saved successfully"); } else {
		  System.out.println("customer cannot saved "); }
		  
		  
		  Customer cust= new Customer(); cust.setCustId("pBce"); cust.setCreateDate(new
		  Date(new java.util.Date().getTime())); cust.setCust_name("prakas");
		  cust.setEmail("prakas123@gmail.com"); cust.setMobile("8765432356");
		  
		  if (Application.customerRepository.saveCustomer(cust)) {
		  System.out.println("customer saved successfully"); } else {
		  System.out.println("customer cannot saved "); }
		 
		
	}

}
