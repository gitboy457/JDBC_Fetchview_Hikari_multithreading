package com.ace.jdbcDemo.app.thread;

import com.ace.jdbcDemo.app.Application;

public class GetCustomerListThread implements Runnable {

	@Override
	public void run() {
		System.out.println(	Application.customerRepository.AllCustomer());	
		
	}

}
