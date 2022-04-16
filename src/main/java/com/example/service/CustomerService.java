package com.example.service;

import java.util.List;

import com.example.entity.Customer;

public interface CustomerService {
	List<Customer> findAll();
	Customer findCustomerById(Integer id);
	Customer findByUserName(String userName);
	Customer update(Customer customer);
	Customer insert(Customer customer);
	void delete(Integer id);
	List<String> checkCustomer(Customer customer);
}
