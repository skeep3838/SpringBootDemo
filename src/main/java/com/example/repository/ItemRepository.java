package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item>{

}
