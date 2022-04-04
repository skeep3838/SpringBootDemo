package com.example.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository dao;
	
	@Override
	public List<Customer> findAll() {
		return dao.findAll();
	}

	@Override
	public Customer findCustomerById(Integer cid) {
		return dao.findByCid(cid);
	}

	@Override
	public Customer save (Customer customer){
		return dao.saveAndFlush(customer);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
