package com.trade.app.login.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
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

	@Value("${upstock.code.key}")
	private String cacheKey;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

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
	public UpstockCode saveAccessCode(String code) {

		UpstockCode upstockCode = new UpstockCode();
		upstockCode.setCode(code);
		upstockCode.setCreatedAt(LocalDateTime.now().toString());

		redisTemplate.opsForValue().set(cacheKey, upstockCode, Duration.ofMinutes(10));

		return upstockCode;
	}

	@Override
	public UpstockCode getAccessCode() {
		// TODO Auto-generated method stub
		Optional<UpstockCode> code = Optional.ofNullable((UpstockCode) redisTemplate.opsForValue().get(cacheKey));
				
		return code.orElseThrow(() -> new RuntimeException());
		}

}
