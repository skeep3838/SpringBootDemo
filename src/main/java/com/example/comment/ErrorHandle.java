package com.example.comment;

import java.util.ArrayList;
import java.util.List;

import com.example.response.ResponseBodyEntity;

public class ErrorHandle {
	/*
	 * 錯誤處理預設格式
	 */
	public static <T> ResponseBodyEntity<T> errHandler(Exception e, String errMsg, ResponseBodyEntity<T> res){
		List<String> errMsgs = new ArrayList<>();
		errMsgs.add(errMsg);
		res.failSet(errMsg);
		res.setErrorMessages(errMsgs);
		return res;
	}
}
