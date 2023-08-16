package com.example.Tax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/service")
@Controller
public class ServController {

	@GetMapping("/agency")
	public String agency() {
		return "pages/service/agency";
	}

	@GetMapping("/claim")
	public String claim() {
		return "pages/service/claim";
	}
	
	@GetMapping("/consulting")
	public String consulting() {
		return "pages/service/consulting";
	}
}
