package com.trade.app.login.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import com.trade.app.auth_lib.util.JwtUtil;
import com.trade.app.login.dto.LoginUserRequest;
import com.trade.app.login.dto.LoginUserResponseDTO;
import com.trade.app.login.entity.LoginUser;
import com.trade.app.login.entity.UpstockCode;
import com.trade.app.login.exception.LoginUserCommonException;
import com.trade.app.login.exception.UserAlreadyExist;
import com.trade.app.login.repository.LoginUserRepository;
import com.trade.app.login.service.LoginUserService;

@Service
public class LoginUserServiceImpl implements LoginUserService {

	@Autowired
	LoginUserRepository loginUserRepository;

	@Autowired
	JwtUtil util;
	
	@Autowired
	RedisCacheManager redisCacheManager;

	@Override
	public LoginUser saveUser(LoginUser user) {
		// TODO Auto-generated method stub
		try {
			loginUserRepository.findByUsername(user.getUsername()).ifPresent(existing -> {
				throw new UserAlreadyExist("User already present, please try with different user", "LU-001");
			});
			return loginUserRepository.save(user);
		} catch (UserAlreadyExist e) {
			throw e;
		} catch (LoginUserCommonException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public LoginUserResponseDTO loginUser(LoginUserRequest loginUserRequest) {
		// TODO Auto-generated method stub
		return loginUserRepository.findByUsername(loginUserRequest.username())
				.filter(user -> user.getPassword().equals(loginUserRequest.password()))
				.map(user -> authenticateUser(loginUserRequest))
				.orElseThrow(() -> new RuntimeException("Invalid username or password"));
	}

	private LoginUserResponseDTO authenticateUser(LoginUserRequest loginUserRequest) {
		// util.generateToken(loginUserRequest.username());
		return new LoginUserResponseDTO(util.generateToken(loginUserRequest.username()));
	}

	@Override
	@Cacheable(value = "upstockCode", key = "code")
	public UpstockCode saveAccessCode(String code) {
		// TODO Auto-generated method stub
		UpstockCode upstockNewCode = new UpstockCode();
		try {
			String op = (String) Optional.ofNullable(code).orElseThrow(() -> new Exception("Empty code"));

			upstockNewCode.setCode(op);
			upstockNewCode.setCreatedAt(LocalDateTime.now());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return upstockNewCode;
	}

	@Override
	public UpstockCode getAccessCode() {
		// TODO Auto-generated method stub
		return (UpstockCode) redisCacheManager.getCache("upstockCode").get("code");
	}

}
