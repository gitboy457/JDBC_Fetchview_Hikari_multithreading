package com.ace.jdbcDemo.repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import com.ace.jdbcDemo.entity.Customer;

public class CustomerRepositoryImp implements CustomerRepository {

	private HikariDBConfig hikariDBConfig;

	public CustomerRepositoryImp() {
		this.hikariDBConfig = new HikariDBConfig();
	}

	@Override
	public boolean saveCustomer(Customer customer) {
		// object of connection is provided by hikari
		// hikari has pool of connection every thread request for connection object
		// if pool has connection object free then it will provide that connection
		// object
		// if pool is not free then the thread should be in waiting state
		Connection con = hikariDBConfig.getNewDBConnection();
		Savepoint sp = null;
		try {
			System.out.println("connection object :-  " + con);
			PreparedStatement pstm = con.prepareStatement(CustomerConstants.INSERT_CUSTOMER);
			pstm.setString(1, customer.getCustId());
			pstm.setString(2, customer.getCust_name());
			pstm.setString(3, customer.getEmail());
			pstm.setString(4, customer.getMobile());
			pstm.setDate(5, customer.getCreateDate());
			// create savepoint
			// savepoints should be created before calling execute statement
			sp = con.setSavepoint(customer.getCustId() + "Savepoint1");
			int i = pstm.executeUpdate();
			if (i == 1) {

				con.commit();
				con.close();

				return true;
			}

		} catch (SQLException e) {
			try {
				// if any exception occure in the insersation of data then rollback all the un
				// commited changes
				con.rollback(sp);
				// con.rollback();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public Customer getByCustId(String CustId) {
		// get connection object from hikari Connection Pool
		Connection con = hikariDBConfig.getNewDBConnection();
		Customer customer = null;
		try {
			EntityMapper emap = new EntityMapper();
			System.out.println("connection object :-  " + con);
			// prepare query with prepared Statment
			PreparedStatement pstm = con.prepareStatement(CustomerConstants.FIND_BY_CUSTID);
			pstm.setString(1, CustId);

			// execute query which return result set
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {

				// mapping resultset to customer
				customer = emap.toCustomer(rs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return customer;
	}

	@Override
	public Customer getpersonInfoView() {
		// get connection object from hikari Connection Pool
		Connection con = hikariDBConfig.getNewDBConnection();

		Customer customer = null;
		EntityMapper emap = new EntityMapper();
		try {
			con.setReadOnly(true);
			System.out.println("connection object :-  " + con);
			// prepare query with prepared Statment
			PreparedStatement pstm = con.prepareStatement(CustomerConstants.GET_PERSON_INFO_VIEW);
			// execute query which return result set
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {

				// mapping resultset to customer
				customer = emap.toPersonView(rs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return customer;
	}

	@Override
	public List<Customer> AllCustomer() {
	
		int count = 0;
		//to fetch list of record first fetch  total row count and set the fetch size of pstm
		int fetchSize=getrowCount();
		// get connection object from hikari Connection Pool
		Connection con = hikariDBConfig.getNewDBConnection();

		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = null;

		EntityMapper emap = new EntityMapper();
		try {
			// con.setReadOnly(true);
			System.out.println("connection object :-  " + con);
			// prepare query with prepared Statment

			PreparedStatement pstm = con.prepareStatement(CustomerConstants.GET_ALL_CUSTOMER_LIST);
			
			//set number of rows to be fetch
			pstm.setFetchSize(fetchSize);
			pstm.setLargeMaxRows(40);
			// execute query which return result set
			ResultSet rs = pstm.executeQuery();
			rs.setFetchSize(40);

			System.out.println(pstm.getFetchSize());
			// in resultset we get multiple record
			while (rs.next()) {

				// mapping resultset to customer
				customer = emap.toCustomer(rs);

				// add every record to list
				customers.add(customer);
				count++;
			}

			while (rs.next()) {

				// mapping resultset to customer
				customer = emap.toCustomer(rs);

				// add every record to list
				customers.add(customer);
				count++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		System.out.println("total number of record :- " + count);
		return customers;
	}

	private int getrowCount() {
		// get connection object from hikari Connection Pool
		int row_count = 0;
		Connection con = hikariDBConfig.getNewDBConnection();

		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = null;

		EntityMapper emap = new EntityMapper();
		try {
			// con.setReadOnly(true);
			System.out.println("connection object :-  " + con);
			// prepare query with prepared Statment

			PreparedStatement pstm = con.prepareStatement(CustomerConstants.GET_ROW_COUNT);
			// pstm.setFetchSize(40);

			// execute query which return result set
			ResultSet rs = pstm.executeQuery();
			// in resultset we get multiple record
			while (rs.next()) {
				row_count = rs.getInt("row_count");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return row_count;
	}
}
