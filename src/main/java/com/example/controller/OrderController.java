package com.example.controller;

import java.util.ArrayList;
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

import com.example.entity.Orders;
import com.example.response.ResponseBodyEntity;
import com.example.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/findOrderByOid")
	public ResponseBodyEntity<Orders> findOrderByOid(HttpServletRequest request) {
		ResponseBodyEntity<Orders> res = new ResponseBodyEntity<Orders>();
		try {
			List<Orders> orderList = orderService.findOrderByOid(1);
			res.setReturnData(orderList);
			res.successSet();
			res.setReturnMessage("查詢訂單"+res.getReturnMessage());
		} catch (Exception e) {
			logger.debug("order/findOrderByOid() ");
			res = errHandler(e, "OrderController：查詢訂單：執行錯誤");
			ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * 錯誤處理預設格式
	 */
	private ResponseBodyEntity<Orders> errHandler(Exception e, String errMsg){
		ResponseBodyEntity<Orders> res = new ResponseBodyEntity<Orders>();
		List<String> errMsgs = new ArrayList<>();
		errMsgs.add(errMsg);
		res.failSet(errMsg);
		res.setErrorMessages(errMsgs);
		return res;
	}
}
