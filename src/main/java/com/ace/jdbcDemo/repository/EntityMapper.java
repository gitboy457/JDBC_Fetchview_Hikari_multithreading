package com.ace.jdbcDemo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ace.jdbcDemo.entity.Customer;

public class EntityMapper {

	
	public Customer toCustomer(ResultSet rs) {
		Customer customer = new Customer();
	
		try {
			customer.setCustId(rs.getString("cust_id"));
			customer.setCust_name(rs.getString("cust_name"));
			customer.setEmail(rs.getString("email"));
			customer.setMobile("mobile");
			customer.setCreateDate(rs.getDate("create_date"));
			customer.setUpdateDate(rs.getDate("update_date"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public Customer toPersonView(ResultSet rs) {
		Customer customer = new Customer();
	
		try {
			customer.setCustId(rs.getString("ID"));
			customer.setCust_name(rs.getString("NAME"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}
}
