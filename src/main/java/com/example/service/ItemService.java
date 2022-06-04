package com.example.service;

import java.util.List;

import com.example.entity.Item;

public interface ItemService {

	List<Item> queryItemByTypeAndPrice(String type, Integer price);
}
