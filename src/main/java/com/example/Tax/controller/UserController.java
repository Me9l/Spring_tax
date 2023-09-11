package com.example.Tax.controller;

import org.glassfish.jaxb.runtime.v2.runtime.output.Encoded;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Tax.dto.UserForm;
import com.example.Tax.dto.UserUpdateForm;
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

		if (bindingResult.hasErrors()) {
			return "common/fragments/signup";
		}

		if (userSecurityService.alreadyTakenEmail(userForm.getUsername())) {
			bindingResult.rejectValue("email", "error.email", "이미 가입된 이메일입니다.");
		}

		try {
			UserEntity user = UserEntity.createUser(userForm, passwordEncoder);
			userSecurityService.saveUser(user);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "common/fragments/signup";
		}

		return "redirect:/";
	}

	// 사용자 정보 조회
	@GetMapping("/info")
	public String userDetail(UserUpdateForm userForm, BindingResult bindingResult, Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();
		UserEntity user = userSecurityService.getUser(email);

		if (!email.equals("anonymousUser")) {
			userForm.setEmail(user.getEmail());
			userForm.setUsername(user.getUsername());
			userForm.setTel(user.getTel());
			model.addAttribute("regdate", user.getRegdate());
		}

		return "pages/service/userDetail";
	}

	// 사용자 정보 업데이트
	@PostMapping("/info/update")
	public String infoUpdate(@Valid UserUpdateForm userUpdateForm, BindingResult bindingResult, Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();
		UserEntity user = userSecurityService.getUser(email);
		String currentPassword = userUpdateForm.getPassword1();
		model.addAttribute("regdate", user.getRegdate());
		
		if (bindingResult.hasErrors()) {
			return "pages/service/userDetail";
		}
		
		if( !passwordEncoder.matches( currentPassword, user.getPassword() ) ){
			bindingResult.rejectValue("password1", "currentPasswordMismatch", "비밀번호가 일치하지 않습니다.");
			return "pages/service/userDetail";
		}

		try {
			if ( !userUpdateForm.getPassword2().isEmpty() ) {				
				user.setPassword(passwordEncoder.encode(userUpdateForm.getPassword2()));
			}
			user.setTel(userUpdateForm.getTel());
			user.setPurpose(userUpdateForm.getPurpose());
			userSecurityService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getMessage());
			return "pages/service/userDetail";
		}

		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String deleteUser(UserUpdateForm userUpdateForm, BindingResult bindingResult, Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		UserEntity user = userSecurityService.getUser(email);
		String currentPassword = user.getPassword();
		
		if (bindingResult.hasErrors()) {
			return "pages/service/userDetail";
		}
		
		if( !passwordEncoder.matches( currentPassword, user.getPassword() ) ){
			bindingResult.rejectValue("password1", "currentPasswordMismatch", "비밀번호가 일치하지 않습니다.");
			return "pages/service/userDetail";
		}

		try {
			userSecurityService.deleteUser(user);
			return "redirect:/user/logout";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}
