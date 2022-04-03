package com.example.contriller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Customer;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
//	@Autowired
//	private CustomerService customerService;
	
	@PostMapping(value = "/login")
	public String login(@RequestBody Customer customer, Map<String, Object> map) {
		if(!StringUtils.isEmpty(customer.getUserName()) && "000000".equals(customer.getPassword())) {
			return "登錄成功";
		}
		return "登錄失敗";
	}
}
