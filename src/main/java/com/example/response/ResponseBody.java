package com.example.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseBody<T> {

	private String transactionId = "";
	private String errorMessage = "";
	private String returnMessage = "";
	private String returnCode = "";
	private List<T> returnData = new ArrayList<>();

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public List<T> getReturnData() {
		return returnData;
	}

	public void setReturnData(List<T> returnData) {
		this.returnData = returnData;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

}
