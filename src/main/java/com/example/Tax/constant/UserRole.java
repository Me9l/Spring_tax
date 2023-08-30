package com.example.Tax.constant;

import lombok.Getter;

@Getter
public enum UserRole {
	// 로그인 후 사용자의 권한 적용	
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	private String value;

	UserRole(String value) {
		this.value = value;
	}
	
}
