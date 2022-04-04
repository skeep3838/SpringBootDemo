package com.example.contriller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.CommonParam;
import com.example.entity.Customer;
import com.example.response.ResponseBody;
import com.example.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	/*
	 * 查詢所有客戶
	 */
	@GetMapping("/findAllCustomer")
    public ResponseBody<Customer> findAllCustomer(HttpServletRequest request){
		ResponseBody<Customer> res = new ResponseBody<Customer>();
		List<Customer> customerList = customerService.findAll();
		res.setTransactionId(UUID.randomUUID().toString());
		res.setReturnCode(CommonParam.SUCCESS_RETURNCODE);
		res.setReturnData(customerList);
        return res;
    }
	
	/*
	 * 新增客戶
	 */
	@PostMapping("/insertCustomer")
	public ResponseBody<Customer> insertsCustomer(HttpServletRequest request, @RequestBody Customer customer) {
		ResponseBody<Customer> res = new ResponseBody<Customer>();
		List<Customer> returnData = new ArrayList<>();
		customer.setUpdateDate(new Date());
		customer.setCreatedDate(new Date());
		Customer customerRes = customerService.save(customer);
		returnData.add(customerRes);
		res.setTransactionId(UUID.randomUUID().toString());
		res.setReturnCode(CommonParam.SUCCESS_RETURNCODE);
		res.setReturnData(returnData);
		return res;
	}
	
	/*
	 * 修改客戶資料
	 */
	@PutMapping("/updateCustomer")
	public ResponseBody<Customer> saveCustomer(HttpServletRequest request, @RequestBody Customer customer) {
		ResponseBody<Customer> res = new ResponseBody<Customer>();
		List<Customer> returnData = new ArrayList<>();
		customer.setUpdateDate(new Date());
		Customer customerRes = customerService.save(customer);
		returnData.add(customerRes);
		res.setTransactionId(UUID.randomUUID().toString());
		res.setReturnCode(CommonParam.SUCCESS_RETURNCODE);
		res.setReturnData(returnData);
		return res;
	}
}
