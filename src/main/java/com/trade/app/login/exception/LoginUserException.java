package com.trade.app.login.exception;

import java.util.HashMap;
import java.util.Map;

public class LoginUserException {
	String msg;
	String errorId;
	String apiPath;
	Map<String, String> errors = new HashMap<>();

	public LoginUserException(String msg, String errorId, String apiPath) {
		super();
		this.msg = msg;
		this.errorId = errorId;
		this.apiPath = apiPath;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "LoginUserException [msg=" + msg + ", errorId=" + errorId + ", apiPath=" + apiPath + ", errors=" + errors
				+ "]";
	}

}
