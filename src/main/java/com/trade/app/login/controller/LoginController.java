package com.trade.app.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trade.app.login.dto.LoginUserRequest;
import com.trade.app.login.dto.LoginUserResponseDTO;
import com.trade.app.login.entity.LoginUser;
import com.trade.app.login.service.LoginUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	LoginUserService service;

	@PostMapping("/save-user")
	public ResponseEntity<LoginUser> saveLoginUser(@Valid @RequestBody LoginUser user) {
		LoginUser savedUser = service.saveUser(user);
		return new ResponseEntity<LoginUser>(savedUser, HttpStatus.CREATED);

	}

	@PostMapping("/login-user")
	public ResponseEntity<LoginUserResponseDTO> loginUser(@Valid @RequestBody LoginUserRequest loginUserRequest) {
		LoginUserResponseDTO savedUser = service.loginUser(loginUserRequest);
		return new ResponseEntity<LoginUserResponseDTO>(savedUser, HttpStatus.CREATED);

	}

}
