package com.example.Tax.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateForm {

	private String email;

	private String username;
	
	@NotBlank(message = "비밀번호를 입력하세요.")
	private String password1;

	@Length(min=0, max=16, message="6자 이상, 16자 이하로 입력하세요.")
	private String password2;

	private String tel;

	private String purpose;
}
