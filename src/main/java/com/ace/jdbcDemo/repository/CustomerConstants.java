package com.ace.jdbcDemo.repository;

public class CustomerConstants {

	static String INSERT_CUSTOMER="insert into customer(cust_id,cust_name,email,mobile,create_date) values(?,?,?,?,?)";
	static String FIND_BY_CUSTID="select * from customer where cust_id= ?";
	static String GET_PERSON_INFO_VIEW="SELECT * FROM bank.get_person_info";
	static String GET_ALL_CUSTOMER_LIST="select * from customer";
	static String GET_ROW_COUNT="select count(*) row_count from customer";
}
