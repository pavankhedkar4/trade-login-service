package com.trade.app.login.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserRole {
	@JsonProperty("admin")
	ADMIN, @JsonProperty("user")
	USER
}
