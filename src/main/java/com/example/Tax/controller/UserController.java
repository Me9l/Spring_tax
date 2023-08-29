package com.example.Tax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Tax.entity.UserEntity;
import com.example.Tax.service.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signUp() {
		return "pages/signup";
	}
	
	
	@PostMapping("/signup")
	public String signUp(
			@RequestParam(value = "userid") String userid,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "tel") String tel
			) {
		
		UserEntity userEntity =
				userService.create(userid, password, email, tel);
		System.out.println(userEntity.getUserid());
		System.out.println(userEntity.getEmail());
		System.out.println(userEntity.getPassword());
		System.out.println(userEntity.getTel());
		return "redirect:/";
		
	}
}
