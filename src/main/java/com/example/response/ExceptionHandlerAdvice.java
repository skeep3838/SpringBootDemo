package com.example.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * 全域錯誤處理
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(UnexpectedRollbackException.class)
    public ResponseEntity<ResponseBodyEntity<String>> handleRollbackException(UnexpectedRollbackException e) {
		ResponseBodyEntity<String> res = new ResponseBodyEntity<>();
		List<String> errMsgs = new ArrayList<>();
		errMsgs.add("全域：unique key重複，無法新增");
		res.failSet("全域：unique key重複，無法新增");
		res.setErrorMessage(e.toString());
		res.setErrorMessages(errMsgs);
		// log exception
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
    } 
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBodyEntity<String>> handleException(Exception e) {
		ResponseBodyEntity<String> res = new ResponseBodyEntity<>();
		List<String> errMsgs = new ArrayList<>();
		errMsgs.add("全域：功能執行錯誤");
		res.failSet("全域：功能執行錯誤");
		res.setErrorMessage(e.toString());
		res.setErrorMessages(errMsgs);
		// log exception
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
    }   
}
