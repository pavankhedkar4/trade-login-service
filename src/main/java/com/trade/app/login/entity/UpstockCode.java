package com.trade.app.login.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UpstockCode {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long codeId;

	private LocalDateTime createdAt;

	private String code;

	public UpstockCode() {

	}

	public UpstockCode(Long codeId, LocalDateTime createdAt) {
		super();
		this.createdAt = createdAt;
	}

	public UpstockCode(LocalDateTime createdAt) {
		super();
		this.createdAt = createdAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}