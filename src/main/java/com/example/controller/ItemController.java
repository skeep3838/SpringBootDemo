package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.comment.ErrorHandle;
import com.example.entity.Item;
import com.example.response.ResponseBodyEntity;
import com.example.service.ItemService;

@RestController
@RequestMapping("item")
public class ItemController {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/findItem")
	public ResponseBodyEntity<Item> findItem(HttpServletRequest request) {
		ResponseBodyEntity<Item> res = new ResponseBodyEntity<>();
		try {
			List<Item> customerList = itemService.queryItemByTypeAndPrice("2", 95);
			res.successSet();
			res.setReturnMessage("依條件查詢商品"+res.getReturnMessage());
			res.setReturnData(customerList);
		} catch (Exception e) {
			logger.debug("item/findItem() ");
			res = ErrorHandle.errHandler(e, "customerController：依條件查詢商品：執行錯誤", new ResponseBodyEntity<Item>());
			ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
			e.printStackTrace();
		}
		return res;
	}
}
