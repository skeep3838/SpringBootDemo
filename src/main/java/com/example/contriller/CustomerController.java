package com.example.contriller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseBody<Customer> findAllCustomer(HttpServletRequest request) {
		ResponseBody<Customer> res = new ResponseBody<Customer>();
		List<Customer> customerList = customerService.findAll();
		res.setTransactionId(UUID.randomUUID().toString());
		res.setReturnCode(CommonParam.SUCCESS_RETURNCODE);
		res.setReturnData(customerList);
		return res;
	}

	/*
	 * 查詢單一客戶
	 */
	@GetMapping("/findCustomerById")
	public ResponseBody<Customer> findCustomerById(HttpServletRequest request, @RequestParam("id") Integer id) {
		ResponseBody<Customer> res = new ResponseBody<Customer>();
		List<Customer> customerList = new ArrayList<>();
		try {
			Customer customerRes = customerService.findCustomerById(id);
			if (customerRes != null)
				customerList.add(customerRes);
			res.setReturnCode(CommonParam.SUCCESS_RETURNCODE);
			res.setReturnMessage("查詢成功");
			res.setReturnData(customerList);
		} catch (Exception e) {
			res.setReturnCode(CommonParam.FAIL_RETURNCODE);
			res.setReturnMessage("查詢失敗");
			res.setErrorMessage(e.toString());
		}
		res.setTransactionId(UUID.randomUUID().toString());
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

	/*
	 * 刪除客戶
	 */
	@DeleteMapping("/deleteCustomer")
	public ResponseBody<Customer> deleteCustomer(HttpServletRequest request, @RequestBody Customer customer) {
		ResponseBody<Customer> res = new ResponseBody<Customer>();
		try {
			customerService.delete(customer.getCid());
			res.setReturnCode(CommonParam.SUCCESS_RETURNCODE);
			res.setReturnMessage("刪除成功");
		} catch (Exception e) {
			res.setReturnCode(CommonParam.FAIL_RETURNCODE);
			res.setReturnMessage("刪除失敗");
			res.setErrorMessage(e.toString());
		}
		res.setTransactionId(UUID.randomUUID().toString());
		return res;
	}
}
