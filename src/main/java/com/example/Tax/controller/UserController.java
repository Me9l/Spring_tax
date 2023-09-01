package com.example.Tax.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Tax.dto.UserForm;
import com.example.Tax.entity.UserEntity;
import com.example.Tax.service.UserSecurityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserSecurityService userSecurityService;
	private final PasswordEncoder passwordEncoder;
	
	// 회원가입 페이지 이동
	@GetMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "common/fragments/signup";
	}
	
	// 회원가입 요청
	@PostMapping("/signup")
	public String signUp(@Valid UserForm userForm, BindingResult bindingResult, Model model) {
		if ( bindingResult.hasErrors() ) {
			return "common/fragments/signup";
		}
		
		try {
			UserEntity user = UserEntity.createUser(userForm, passwordEncoder);
			userSecurityService.saveUser(user);
			} catch (IllegalStateException e) {
				model.addAttribute("errorMessage",e.getMessage());
				return "common/fragments/signup";
			}
		return "redirect:/";
	}
}
