package com.example.Tax.controller;

import java.security.Principal;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Tax.dto.BoardForm;
import com.example.Tax.entity.UserEntity;
import com.example.Tax.service.BoardService;
import com.example.Tax.service.UserSecurityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class adminController {

	private final BoardService boardService;
	private final UserSecurityService userSecurityService;


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public String admin() {
		return "pages/admin/adminMenu";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admininquire")
	public String inquire() {
		return "pages/admin/adminInquire";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admindata")
	public String data() {
		return "pages/admin/adminData";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/adminuser")
	public String userData() {
		return "pages/admin/adminuser";
	}
	
	//게시글 작성 페이지
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/post")
	public String post(BoardForm boardForm, Model model) {
		model.addAttribute("boardForm",boardForm);
		return "pages/admin/boardForm";
	}
	
	// 게시글 작성
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/post")
	public String post(@Valid BoardForm boardForm, BindingResult bindingResult, Principal principal, Model model) {
		
		if ( bindingResult.hasErrors() ) {
			model.addAttribute("boardForm",boardForm);
			return "pages/admin/boardForm";
		}
			UserEntity user = userSecurityService.getUser(principal.getName());
			boardService.saveBoard(boardForm.getTitle(),boardForm.getContent(),boardForm.getCategory(),user);
		return "redirect:/boards/news";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDeniedException() {
		return "redirect:/";
	}

}
