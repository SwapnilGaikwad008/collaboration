package com.niit.collaboration.model;


import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
public class BaseDomain
{

	@Transient
	public String errorCode;

	@Transient
	public String errorMsg;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}

