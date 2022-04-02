package com.example.contriller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
	@GetMapping("/")
    public String hello(){
        return "hello, Nicole!";
    }
}
