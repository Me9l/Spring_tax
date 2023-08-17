package com.example.Tax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/boards")
@Controller
public class BoardController {

	@GetMapping("/news")
	public String news() {
		return "pages/board/board";
	}

	@GetMapping("/inside")
	public String inside() {
		return "pages/board/board";
	}

	@GetMapping("/data")
	public String data() {
		return "pages/board/board";
	}
}
