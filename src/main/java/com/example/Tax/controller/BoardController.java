package com.example.Tax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/boards")
@RequiredArgsConstructor
@Controller
public class BoardController {
	
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

}
