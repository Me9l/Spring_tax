package com.example.Tax.constant;

import lombok.Getter;

@Getter
public enum Role {
	ADMIN("ADMIN_ROLE"),
	USER("USER_ROLE");
	
	private String value;

	Role(String value) {
		this.value = value;
	}
}
