package com.trade.app.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trade.app.login.entity.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, String> {
	public Optional<LoginUser> findByUsername(String username);
}
