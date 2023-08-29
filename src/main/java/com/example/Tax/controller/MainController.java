package com.example.Tax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
	
	@GetMapping("/{seq}")
	public String main(@PathVariable("seq") String string) {
		if ( string.equals("inquiry")) {			
			return "pages/inquiry";
		}
		if ( string.equals("")||string.equals("index") ) {
			return "index";
		}
		return "index";
		}
	}
	
//	@GetMapping("/index")
//	public String index() {
//		return "index";
//	}
//
//	@GetMapping("/inquiry")
//	public String inquiry() {
//		return "pages/inquiry";
//	}
//	
//}
