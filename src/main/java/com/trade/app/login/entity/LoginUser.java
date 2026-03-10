package com.trade.app.login.entity;

import com.trade.app.login.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
//import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Size;

@Entity
public class LoginUser {

	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	/*
	 * @SequenceGenerator( name = "user_seq", sequenceName = "login_user_seq",
	 * //Actual DB sequence, auto update if used property
	 * hibernate.hbm2ddl.auto=update allocationSize = 1 )
	 */
	// @GenericGenerator(name = "user_seq", strategy =
	// "com.trade.app.login.config.UserIdGenerator")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String username;

	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
	private String password;

	private UserRole role = UserRole.USER;

	private String name;

	private String emailId;

	private String upstockId;

	public LoginUser() {
	}

	public LoginUser(long id, String username,
			@Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character") String password,
			UserRole role, String name, String emailId, String upstockId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.name = name;
		this.emailId = emailId;
		this.upstockId = upstockId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUpstockId() {
		return upstockId;
	}

	public void setUpstockId(String upstockId) {
		this.upstockId = upstockId;
	}

	@Override
	public String toString() {
		return "LoginUser [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}

}
