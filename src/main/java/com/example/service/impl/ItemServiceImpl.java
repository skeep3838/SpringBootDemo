package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.entity.Item;
import com.example.repository.ItemRepository;
import com.example.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository; 

	@Override
	public List<Item> queryItemByTypeAndPrice(String type, Integer price){
		return itemRepository.findAll(queryItemBy(type, price));
	}
	
	public Specification<Item> queryItemBy(String type, Integer price) {
		return (itemRoot, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (StringUtils.isNotBlank(type)) {
				predicates.add(cb.equal(itemRoot.get("type"), type));
			}
			if (price != null) {
				predicates.add(cb.equal(itemRoot.get("price"), price));
			}
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

}
