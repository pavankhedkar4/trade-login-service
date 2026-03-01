package com.trade.app.login.service.impl;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trade.app.login.entity.LoginUser;
import com.trade.app.login.exception.LoginUserCommonException;
import com.trade.app.login.exception.UserAlreadyExist;
import com.trade.app.login.repository.LoginUserRepository;
import com.trade.app.login.service.LoginUserService;

@Service
public class LoginUserServiceImpl implements LoginUserService {

	@Autowired
	LoginUserRepository loginUserRepository;

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

}
