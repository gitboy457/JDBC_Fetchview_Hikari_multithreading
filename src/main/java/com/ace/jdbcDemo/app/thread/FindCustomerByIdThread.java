package com.ace.jdbcDemo.app.thread;

import com.ace.jdbcDemo.app.Application;

public class FindCustomerByIdThread implements Runnable{

	@Override
	public void run() {
		//execute select statement
			System.out.println(	Application.customerRepository.getByCustId("anil123"));
		
	}

}
