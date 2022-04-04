package com.example.service;

import java.util.List;

import com.example.entity.Customer;

public interface CustomerService {
	List<Customer> findAll();
	Customer findCustomerById(Integer id);
	Customer save(Customer customer);
	void delete(Integer id);
}
