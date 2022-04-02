package com.example.contriller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Customer;
import com.example.service.impl.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/findAllCustomer")
    public List<Customer> findAllCustomer(HttpServletRequest request){
		List<Customer> customerList = customerService.findAll();
        return customerList;
    }
	
	@PutMapping("/saveCustomer")
	public Customer saveCustomer(HttpServletRequest request, @RequestBody Customer customer) {
		return customerService.save(customer);
	}
}
