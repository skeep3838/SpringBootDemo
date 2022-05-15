package com.example.service;

import java.util.List;

import com.example.entity.Orders;

public interface OrderService {
	List<Orders> findOrderByOid(Integer oid);
}
