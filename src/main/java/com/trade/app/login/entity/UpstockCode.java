package com.trade.app.login.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UpstockCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long codeId;

	private String createdAt;

	private String code;

	public UpstockCode() {

	}

	public UpstockCode(Long codeId, String createdAt) {
		super();
		this.createdAt = createdAt;
	}

	public UpstockCode(String createdAt) {
		super();
		this.createdAt = createdAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}