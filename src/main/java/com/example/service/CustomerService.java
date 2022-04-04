package com.example.service;

import java.util.List;

import com.example.entity.Customer;
import com.example.response.ResponseBody;

public interface CustomerService {
	List<Customer> findAll();
	Customer findCustomerById(Integer id);
	Customer save(Customer customer);
	void delete(Integer id);
	ResponseBody<Customer> checkAndSaveCustomer(Customer customer);
}
