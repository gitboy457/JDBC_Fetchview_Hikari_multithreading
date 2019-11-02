package com.ace.jdbcDemo.app;

import java.sql.Date;

import com.ace.jdbcDemo.app.thread.FindCustomerByIdThread;
import com.ace.jdbcDemo.app.thread.GetCustomerListThread;
import com.ace.jdbcDemo.app.thread.SaveRecordThread;
import com.ace.jdbcDemo.entity.Customer;
import com.ace.jdbcDemo.repository.CustomerRepository;
import com.ace.jdbcDemo.repository.CustomerRepositoryImp;

public class Application {

	public static CustomerRepository customerRepository;

	static {
		customerRepository = new CustomerRepositoryImp();
	}

	public static void main(String[] args) {

		SaveRecordThread saveRecord = new SaveRecordThread();
		Thread saveRecordThread = new Thread(saveRecord);
		saveRecordThread.start();
		try {
			saveRecordThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GetCustomerListThread getCustomerList = new GetCustomerListThread();
		Thread getCustomerListThread = new Thread(getCustomerList);
		getCustomerListThread.start();

		FindCustomerByIdThread findCustomerById = new FindCustomerByIdThread();
		Thread findCustomerByIdThread = new Thread(findCustomerById);
		findCustomerByIdThread.start();

		// execute insert query
		/*
		 * Customer customer= new Customer(); customer.setCustId("aB");
		 * customer.setCreateDate(new Date(new java.util.Date().getTime()));
		 * customer.setCust_name("ashif"); customer.setEmail("ashif123@gmail.com");
		 * customer.setMobile("8765432356");
		 * 
		 * if (customerRepository.saveCustomer(customer)) {
		 * System.out.println("customer saved successfully"); } else {
		 * System.out.println("customer cannot saved "); }
		 * 
		 * 
		 * Customer cust= new Customer(); cust.setCustId("pB"); cust.setCreateDate(new
		 * Date(new java.util.Date().getTime())); cust.setCust_name("prakas");
		 * cust.setEmail("prakas123@gmail.com"); cust.setMobile("8765432356");
		 * 
		 * if (customerRepository.saveCustomer(cust)) {
		 * System.out.println("customer saved successfully"); } else {
		 * System.out.println("customer cannot saved "); }
		 */

		// call view
		/*
		 * Customer customer = customerRepository.getpersonInfoView();
		 * System.out.println(customer.getCustId());
		 * System.out.println(customer.getCust_name());
		 */

	}

}
