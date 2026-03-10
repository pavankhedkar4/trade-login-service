package com.trade.app.login.service;

import com.trade.app.login.dto.LoginUserRequest;
import com.trade.app.login.dto.LoginUserResponseDTO;
import com.trade.app.login.entity.LoginUser;

public interface LoginUserService {
	LoginUser saveUser(LoginUser user);
	LoginUserResponseDTO loginUser(LoginUserRequest loginUserRequest );
}
