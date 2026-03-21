package com.trade.app.login.service;

import com.trade.app.login.dto.LoginUserRequest;
import com.trade.app.login.dto.LoginUserResponseDTO;
import com.trade.app.login.entity.LoginUser;
import com.trade.app.login.entity.UpstockCode;

public interface LoginUserService {
	LoginUser saveUser(LoginUser user);
	LoginUserResponseDTO loginUser(LoginUserRequest loginUserRequest );
	UpstockCode saveAccessCode(String code);
	UpstockCode getAccessCode();
}
