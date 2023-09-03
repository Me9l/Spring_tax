package com.example.Tax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Tax.dto.InquireForm;
import com.example.Tax.entity.InquireEntity;
import com.example.Tax.service.InquireService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/service")
@RequiredArgsConstructor
@Controller
public class ServController {

	private final InquireService inquireService;
	
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
	
	@GetMapping("/inquire")
	public String inquire(InquireForm inquireForm) {
		return "pages/inquire";
	}
	
	@PostMapping("/inquire")
	public String inquire(@Valid InquireForm inquireForm, BindingResult bindingResult,Model model) {

		if( bindingResult.hasErrors() ) {
			return "pages/inquire";
		}
		
		try {
			InquireEntity inquire = InquireEntity.createInquire(inquireForm);
			inquireService.saveInquire(inquire);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getMessage());
			return "pages/inquire";
		}
		
		return "redirect:/";
	}
}
