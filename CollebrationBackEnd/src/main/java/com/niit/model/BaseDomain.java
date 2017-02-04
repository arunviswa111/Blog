package com.niit.model;

import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
public class BaseDomain {
	
	@Transient
	private String errorcode;
	
	@Transient
	private String errormessage;

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	
	
	

}





