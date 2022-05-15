package com.example.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Orders;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;

@Transactional
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository dao;
	
	@Override
	public List<Orders> findOrderByOid(Integer oid) {
		return dao.findOrderByOid(oid);
	}

}
