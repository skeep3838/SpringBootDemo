package com.example.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import com.example.response.ResponseBody;
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

	@Override
	public ResponseBody<Customer> checkAndSaveCustomer(Customer customer) {
		ResponseBody<Customer> res = new ResponseBody<Customer>();
		List<String> errMsg = new ArrayList<>();
		if(customer.getUserName().isEmpty()) 
			errMsg.add("帳號不得為空");		
		if(customer.getPassword().isEmpty()) 
			errMsg.add("密碼不得為空");
		if(errMsg.size()>0) {
			res.failSet();
			res.setReturnMessage("欄位填寫不完整");
			res.setErrorMessages(errMsg);
			return res;
		}
		
		List<Customer> customerRtn = new ArrayList<>();
		customer.setUpdateDate(new Date());
		try {
			customerRtn.add(save(customer));
			res.successSet();
			res.setReturnData(customerRtn);
		} catch (UnexpectedRollbackException e){
			res.failSet();
			res.setErrorMessage(e.toString());
		} catch (Exception e) {
			res.failSet();
			res.setErrorMessage(e.toString());
		}	
		return res;
	}

}
