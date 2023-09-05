package com.example.Tax.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Tax.dto.BoardForm;
import com.example.Tax.entity.UserEntity;
import com.example.Tax.service.BoardService;
import com.example.Tax.service.UserSecurityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/boards")
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;
	private final UserSecurityService userSecurityService;
	
	@GetMapping("/news")
	public String news(Model model) {
//		List<BoardEntity> board = boardRepository.findByCategoryLikeOrderByRegdateDesc("news");
//		model.addAttribute(board);
		return "pages/board/board";
	}
	
	@GetMapping("/inside")
	public String inside(Model model) {
//		List<BoardEntity> board = boardRepository.findByCategoryLikeOrderByRegdateDesc("inside");
//		model.addAttribute(board);
		return "pages/board/board";
	}

	@GetMapping("/data")
	public String data(Model model) {
//		List<BoardEntity> board = boardRepository.findByCategoryLikeOrderByRegdateDesc("data");
//		model.addAttribute(board);
		return "pages/board/board";
	}
	
	//게시글 작성 페이지
	@GetMapping("/post")
	public String post(BoardForm boardForm, Model model) {
		model.addAttribute("boardForm",boardForm);
		return "common/fragments/boardForm";
	}
	
	// 게시글 작성
	@PostMapping("/post")
	public String post(@Valid BoardForm boardForm, BindingResult bindingResult, Principal principal, Model model) {
		
		if ( bindingResult.hasErrors() ) {
			model.addAttribute("boardForm",boardForm);
			return "common/fragments/boardForm";
		}
			UserEntity user = userSecurityService.getUser(principal.getName());
			boardService.saveBoard(boardForm.getTitle(),boardForm.getContent(),boardForm.getCategory(),user);
		return "redirect:/boards/news";
	}
}
