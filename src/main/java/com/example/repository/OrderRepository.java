package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
