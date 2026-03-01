package com.trade.app.login.exception;

public class UserAlreadyExist extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;
	String errorCode;
	public UserAlreadyExist(String msg, String errorCode) {
		super(msg);
		this.msg = msg;
		this.errorCode = errorCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
