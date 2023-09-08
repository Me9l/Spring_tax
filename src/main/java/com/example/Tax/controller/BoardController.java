package com.example.Tax.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/news")
	public String news(
			Model model,
			@RequestParam(value="page", defaultValue ="0") int page
			) {
		Page<BoardEntity> paging = boardService.getBoardByCategory(page, "news");
		model.addAttribute("paging",paging);
		return "pages/board/board";
	}
	
	@GetMapping("/inside")
	public String inside(Model model,
		@RequestParam(value="page", defaultValue ="0") int page
		) {
	Page<BoardEntity> paging = boardService.getBoardByCategory(page, "inside");
	model.addAttribute("paging",paging);
		return "pages/board/board";
	}

	@GetMapping("/data")
	public String data(Model model,
			@RequestParam(value="page", defaultValue ="0") int page
			) {
		Page<BoardEntity> paging = boardService.getBoardByCategory(page, "data");
		model.addAttribute("paging",paging);
		return "pages/board/board";
	}

}
