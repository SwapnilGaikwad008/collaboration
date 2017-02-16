package com.niit.collaboration.model;

import javax.persistence.Transient;

public class BaseDomain {
	
	@Transient
	public String errorCode;
	
	@Transient
	public String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode(){
		return errorCode;
	}
}
