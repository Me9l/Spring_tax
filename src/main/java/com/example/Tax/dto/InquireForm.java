package com.example.Tax.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InquireForm {

	@NotBlank(message = "이름을 입력하세요.")
	private String username;
	@NotBlank(message = "전화번호를 입력하세요.")
	private String tel;
	@Email
	@NotBlank
	private String email;
	
	private String purpose;
	
	@Length(min = 10, max = 200, message = "10자 이상 200자 이하로 입력하세요.")
	private String content;
	
	private String Sectors;
	
	private boolean optin;
	
}
