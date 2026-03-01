package com.trade.app.login.exception;

public class LoginUserCommonException extends RuntimeException {
	String msg;
	String errorCode;

	public LoginUserCommonException(String msg, String errorCode) {
		super();
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

}
