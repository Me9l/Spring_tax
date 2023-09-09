package com.example.Tax.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Tax.dto.BoardForm;
import com.example.Tax.entity.BoardEntity;
import com.example.Tax.entity.InquireEntity;
import com.example.Tax.entity.UserEntity;
import com.example.Tax.service.BoardService;
import com.example.Tax.service.InquireService;
import com.example.Tax.service.UserSecurityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class adminController {

	private final BoardService boardService;
	private final InquireService inquireService;
	private final UserSecurityService userSecurityService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public String admin(Model model) {
		return "pages/admin/adminMenu";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admininquire")
	public String inquire(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "keyword", defaultValue = "") String keyword) {
		Page<InquireEntity> paging = inquireService.getInquireList(page, keyword);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		return "pages/admin/adminInquire";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/data")
	public String data(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "keyword", defaultValue = "") String keyword) {
		Page<BoardEntity> paging = boardService.getBoardList(page, keyword);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		return "pages/admin/adminData";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/adminuser")
	public String user(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "keyword", defaultValue = "") String keyword) {
		Page<UserEntity> paging = userSecurityService.getAllUsers(page, keyword);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		return "pages/admin/adminuser";
	}

	// 게시글 작성 페이지
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/post")
	public String post(BoardForm boardForm, Model model) {
		model.addAttribute("boardForm", boardForm);
		return "pages/admin/boardForm";
	}

	// 게시글 작성
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/post")
	public String post(@Valid BoardForm boardForm, BindingResult bindingResult, Principal principal, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("boardForm", boardForm);
			return "pages/admin/boardForm";
		}
		UserEntity user = userSecurityService.getUser(principal.getName());
		boardService.saveBoard(boardForm.getTitle(), boardForm.getContent(), boardForm.getCategory(), user);
		return "redirect:/admin/admindata";
	}

	// 상담 상세 내용 보기
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/inquire/{id}")
	public String inquireDetail(@PathVariable("id") Long id, Model model) {
		InquireEntity inquire = inquireService.getInquireDetail(id);
		model.addAttribute("inquire", inquire);
		return "pages/admin/inquireDetail";
	}

	// 자료 상세보기 및 수정
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/data/{id}")
	public String dataDetail(
			@PathVariable("id") Long id, Model model
			) {
		BoardEntity board = boardService.getBoardDetail(id);
		model.addAttribute("board",board);
		return "pages/admin/dataDetail";
	}

	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDeniedException() {
		return "redirect:/";
	}

}
