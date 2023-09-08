package com.example.Tax.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Tax.entity.BoardEntity;
import com.example.Tax.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/boards")
@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/{category}")
	public String getBoardByCategory(
			Model model,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@PathVariable String category
	) {
		Page<BoardEntity> paging = boardService.getBoardByCategory(page, category);
		model.addAttribute("paging", paging);
		return "pages/board/board";
	}
}
