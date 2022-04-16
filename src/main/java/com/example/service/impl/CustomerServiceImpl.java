package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository dao;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public List<Customer> findAll() {
		return dao.findAll();
	}

	@Override
	public Customer findCustomerById(Integer cid) {
		return dao.findByCid(cid);
	}

	@Override
	public Customer update (Customer customer){
		return dao.saveAndFlush(customer);
	}

	@Override
	public Customer insert(Customer customer) {
		// 新增前，對密碼進行加密
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		return dao.saveAndFlush(customer);
	}
	
	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public Customer findByUserName(String userName) {
		return dao.findByUserName(userName);
	}
	
	@Override
	public List<String> checkCustomer(Customer customer) {
		List<String> errMsg = new ArrayList<>();
		if(customer.getUserName().isEmpty()) 
			errMsg.add("帳號不得為空");		
		if(customer.getPassword().isEmpty()) 
			errMsg.add("密碼不得為空");
		return errMsg;		
	}

}
