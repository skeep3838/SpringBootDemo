package com.example.service.impl;

import java.util.List;

import com.example.entity.Customer;

public interface CustomerService {
	List<Customer> findAll();
	Customer queryCustomerById(Integer id);
	Customer save(Customer customer);
}
