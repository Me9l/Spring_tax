package com.example.Tax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Tax.dto.UserForm;
import com.example.Tax.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signUp(UserForm userForm) {
		return "common/fragments/signup";
	}

	@PostMapping("/signup")
	public String signUp(@Valid UserForm userForm, BindingResult bindingResult) {
		if ( bindingResult.hasErrors() ) {
			return "common/fragments/signup";
		}
		if ( !userForm.getPassword1().equals(userForm.getPassword2()) ) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "비밀번호가 일치하지 않습니다.");
			return "common/fragments/signup";
		}
		try {
			userService.saveUser(userForm);
			} catch (Exception e) {
				e.printStackTrace();
				bindingResult.reject("signUpFailed","이미 등록된 아이디 입니다.");
				bindingResult.reject("signUpFailed",e.getMessage());
				return "common/fragments/signup";
			}
		return "redirect:/";
		
	}
}
