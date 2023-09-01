package com.example.Tax.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserForm {
	
	@NotBlank(message = "이메일을 입력하세요.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;
	
	@NotBlank(message = "이름을 입력하세요.")
	private String username;
	
	@Length(min=6, max=16, message = "비밀번호를 입력하세요. 6자 이상, 16자 이하.")
	private String password1;
	
	@NotBlank(message = "비밀번호를 확인하세요.")
	private String password2;

	@NotBlank(message = " '-' 를 제외한 전화번호 11자리를 입력하세요.")
	private String tel;
	
	private String purpose;
}
