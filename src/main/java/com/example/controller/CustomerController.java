package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.comment.CommonParam;
import com.example.entity.Customer;
import com.example.response.ResponseBodyEntity;
import com.example.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;

	/*
	 * 查詢所有客戶
	 */
	@GetMapping("/findAllCustomer")
	public ResponseBodyEntity<Customer> findAllCustomer(HttpServletRequest request) {
		ResponseBodyEntity<Customer> res = new ResponseBodyEntity<Customer>();
		try {
			List<Customer> customerList = customerService.findAll();
			res.successSet();
			res.setReturnMessage("查詢客戶清單"+res.getReturnMessage());
			res.setReturnData(customerList);
		} catch (Exception e) {
			logger.debug("customer/findAllCustomer() ");
			res = errHandler(e, "customerController：查詢所有資料：執行錯誤");
			ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * 查詢單一客戶
	 */
	@GetMapping("/findCustomerById")
	public ResponseBodyEntity<Customer> findCustomerById(HttpServletRequest request, @RequestParam("id") Integer id) {
		ResponseBodyEntity<Customer> res = new ResponseBodyEntity<Customer>();
		List<Customer> customerList = new ArrayList<>();
		
		try {
			Customer customerRes = customerService.findCustomerById(id);
			if (customerRes != null)
				customerList.add(customerRes);
			res.successSet();
			res.setReturnMessage("查詢客戶"+res.getReturnMessage());
			res.setReturnData(customerList);
		} catch (Exception e) {
			logger.debug("customer/findCustomerById(): cid:{}", id);
			res = errHandler(e, "customerController：查詢客戶資料：執行錯誤");
			ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * 新增客戶
	 */
	@PostMapping("/insertCustomer")
	public ResponseBodyEntity<Customer> insertsCustomer(HttpServletRequest request, @RequestBody Customer customer) {
		customer.setCreatedDate(new Date());	
		customer.setUpdateDate(new Date());
		ResponseBodyEntity<Customer> res = new ResponseBodyEntity<>();
		List<String> errMsgs = customerService.checkCustomer(customer);
		if(errMsgs.size()>0) {
			res.failSet("customerController：欄位填寫不完整");
			res.setErrorMessage("欄位填寫不完整");
			res.setErrorMessages(errMsgs);
			return res;
		}
		
		List<Customer> rtnData = new ArrayList<>();
		try {
			rtnData.add(customerService.insert(customer));
			res.setReturnMessage("新增客戶"+res.getReturnMessage());
			res.successSet();
			res.setReturnData(rtnData);
		} catch (UnexpectedRollbackException e) {
			logger.debug("customer/insertCustomer(): userName:{}", customer.getUserName());
			res = errHandler(e, "customerController：unique key重複，無法新增");
			ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
			e.printStackTrace();
		}catch (Exception e) {
			logger.debug("customer/insertCustomer(): userName:{}", customer.getUserName());
			res = errHandler(e, "customerController：新增客戶資料：執行錯誤");
			ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
			e.printStackTrace();
		}	
		return res;
	}

	/*
	 * 修改客戶資料
	 */
	@PutMapping("/updateCustomer")
	public ResponseBodyEntity<Customer> updateCustomer(HttpServletRequest request, @RequestBody Customer customer) {
		ResponseBodyEntity<Customer> res = new ResponseBodyEntity<>();
		List<String> errMsgs = new ArrayList<>();
		if(customer.getCid()==null) {
			String errMsg = "無輸入Id";
			errMsgs.add(errMsg);
			res.setReturnCode(CommonParam.FAIL_RETURNCODE);
			res.setReturnMessage(errMsg);
			res.setErrorMessages(errMsgs);
			return res;
		}
		
		errMsgs = customerService.checkCustomer(customer);
		if(errMsgs.size()>0) {
			res.setErrorMessage("欄位填寫不完整");
			res.setErrorMessages(errMsgs);
			return res;
		}
		
		List<Customer> rtnData = new ArrayList<>();
		try {
			rtnData.add(customerService.update(customer));
			res.setReturnMessage("修改客戶資料"+res.getReturnMessage());
			res.successSet();
			res.setReturnData(rtnData);
		} catch (UnexpectedRollbackException e) {
			logger.debug("customer/updateCustomer(): userName:{}", customer.getUserName());
			res = errHandler(e, "customerController：unique key重複，無法新增");
			ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
			e.printStackTrace();
		}catch (Exception e) {
			logger.debug("customer/updateCustomer(): userName:{}", customer.getUserName());
			res = errHandler(e, "customerController：修改客戶資料 ：執行錯誤");
			ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
			e.printStackTrace();
		}						
		return res;
	}

	/*
	 * 刪除客戶
	 */
	@DeleteMapping("/deleteCustomer")
	public ResponseBodyEntity<Customer> deleteCustomer(HttpServletRequest request, @RequestBody Customer customer) {
		ResponseBodyEntity<Customer> res = new ResponseBodyEntity<Customer>();
		
		try {
			customerService.delete(customer.getCid());
			res.successSet();
			res.setReturnMessage("刪除客戶"+res.getReturnMessage());
		} catch (Exception e) {
			logger.debug("customer/deleteCustomer(): cid:{}", customer.getCid());
			res = errHandler(e, "customerController：刪除客戶資料執行錯誤");
			ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * 錯誤處理預設格式
	 */
	private ResponseBodyEntity<Customer> errHandler(Exception e, String errMsg){
		ResponseBodyEntity<Customer> res = new ResponseBodyEntity<Customer>();
		List<String> errMsgs = new ArrayList<>();
		errMsgs.add(errMsg);
		res.failSet(errMsg);
		res.setErrorMessages(errMsgs);
		return res;
	}
}
