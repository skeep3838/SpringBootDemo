package com.example.contriller;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.comment.CommonParam;
import com.example.entity.Customer;
import com.example.response.ResponseBody;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
//	@Autowired
//	private CustomerService customerService;
	
	@PostMapping(value = "/login")
	public ResponseBody<Customer> login(@RequestBody Customer customer, Map<String, Object> map) {
		ResponseBody<Customer> res = new ResponseBody<Customer>();
		res.setTransactionId(UUID.randomUUID().toString());
		if(!StringUtils.isEmpty(customer.getUserName()) && "000000".equals(customer.getPassword())) {
			res.setReturnCode(CommonParam.SUCCESS_RETURNCODE);
		}else {
			res.setReturnCode(CommonParam.FAIL_RETURNCODE);
			res.setErrorMessage("登錄失敗");
		}
		return res;
	}
}
