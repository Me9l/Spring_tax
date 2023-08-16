package com.example.Tax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/intro")
@Controller
public class IntroController {
	
	@GetMapping("/about")
	public String about() {
		return "pages/intro/about";
	}
	@GetMapping("/team")
	public String team() {
		return "pages/intro/team";
	}
}
