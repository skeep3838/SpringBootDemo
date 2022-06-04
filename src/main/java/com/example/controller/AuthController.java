package com.example.controller;

import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AuthRequest;
import com.example.security.JWTService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
	@Autowired
    private JWTService jwtService;
	
//	@PostMapping(value = "/login")
//	public ResponseBodyEntity<Customer> login(@RequestBody Customer customer, Map<String, Object> map) {
//		ResponseBodyEntity<Customer> res = new ResponseBodyEntity<Customer>();
//		res.setTransactionId(UUID.randomUUID().toString());
//		if(!StringUtils.isEmpty(customer.getUserName()) && "000000".equals(customer.getPassword())) {
//			res.setReturnCode(CommonParam.SUCCESS_RETURNCODE);
//		}else {
//			res.setReturnCode(CommonParam.FAIL_RETURNCODE);
//			res.setErrorMessage("登錄失敗");
//		}
//		return res;
//	}
	
	@PostMapping
    public ResponseEntity<Map<String, String>> issueToken(@Valid @RequestBody AuthRequest request) {
        String token = jwtService.generateToken(request);
        Map<String, String> response = Collections.singletonMap("token", token);

        return ResponseEntity.ok(response);
    }
	
	@PostMapping("/parse")
    public ResponseEntity<Map<String, Object>> parseToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        Map<String, Object> response = jwtService.parseToken(token);

        return ResponseEntity.ok(response);
    }
}
