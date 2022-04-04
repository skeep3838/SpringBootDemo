package com.example.contriller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.example.comment.CommonParam;
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
		try {
			List<Customer> customerList = customerService.findAll();
			res.successSet();
			res.setReturnMessage("查詢客戶清單"+res.getReturnMessage());
			res.setReturnData(customerList);
		} catch (Exception e) {
			res.failSet();
			res.setReturnMessage("查詢客戶清單"+res.getReturnMessage());
			res.setErrorMessage(e.toString());
		}
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
			res.successSet();
			res.setReturnMessage("查詢客戶"+res.getReturnMessage());
			res.setReturnData(customerList);
		} catch (Exception e) {
			res.failSet();
			res.setReturnMessage("查詢客戶"+res.getReturnMessage());
			res.setErrorMessage(e.toString());
		}
		return res;
	}

	/*
	 * 新增客戶
	 */
	@PostMapping("/insertCustomer")
	public ResponseBody<Customer> insertsCustomer(HttpServletRequest request, @RequestBody Customer customer) {
		customer.setCreatedDate(new Date());
		ResponseBody<Customer> res = customerService.checkAndSaveCustomer(customer);
		res.setReturnMessage("新增客戶"+res.getReturnMessage());
		return res;
	}

	/*
	 * 修改客戶資料
	 */
	@PutMapping("/updateCustomer")
	public ResponseBody<Customer> saveCustomer(HttpServletRequest request, @RequestBody Customer customer) {
		ResponseBody<Customer> res = new ResponseBody<>();
		if(customer.getCid()==null) {
			List<String> errMsgs = new ArrayList<>();
			String errMsg = "無輸入Id";
			errMsgs.add(errMsg);
			res.setReturnCode(CommonParam.FAIL_RETURNCODE);
			res.setReturnMessage(errMsg);
			res.setErrorMessages(errMsgs);
			return res;
		}
		res = customerService.checkAndSaveCustomer(customer);					
		res.setReturnMessage("修改客戶資料"+res.getReturnMessage());
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
			res.successSet();
			res.setReturnMessage("刪除客戶"+res.getReturnMessage());
		} catch (Exception e) {
			res.successSet();
			res.setReturnMessage("刪除客戶"+res.getReturnMessage());
			res.setErrorMessage(e.toString());
		}
		return res;
	}
}
