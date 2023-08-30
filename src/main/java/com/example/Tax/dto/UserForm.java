package com.example.Tax.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserForm {

	@NotBlank(message = "아이디를 입력하세요.")
	private String userid;
	
	@NotBlank(message = "이름을 입력하세요.")
	private String username;
	
	@NotBlank(message = "비밀번호를 입력하세요.")
	@Length(min=6, max=16, message = "8자 이상, 16자 이하로 입력하세요.")
	private String password1;
	
	@NotBlank(message = "비밀번호를 확인하세요.")
	private String password2;
	
	@NotBlank(message = "이메일을 입력하세요.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;
	
	private String tel;
	
	private String purpose;
}
