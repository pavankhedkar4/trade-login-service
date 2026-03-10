package com.trade.app.login.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Configuration
public class UserExceptionHandler {

	@ExceptionHandler(UserAlreadyExist.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public LoginUserException getUserNotPresentException(UserAlreadyExist exception, HttpServletRequest request) {
		LoginUserException exe = new LoginUserException(exception.getMessage(), exception.getErrorCode(),
				request.getRequestURI());
		return exe;

	}
	
	@ExceptionHandler(LoginUserCommonException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public LoginUserException getCommonException(LoginUserCommonException exception, HttpServletRequest request) {
		LoginUserException exe = new LoginUserException(exception.getMessage(), exception.getErrorCode(),
				request.getRequestURI());
		return exe;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<LoginUserException> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors()
	            .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

	    LoginUserException apiError = new LoginUserException(
	    		"Validation failed",
	            "LU-002",
	            request.getRequestURI()
	    );
	    apiError.setErrors(errors);

	    return ResponseEntity.badRequest().body(apiError);
	}
	

}
