package com.example.Tax.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardForm {

	@NotBlank(message = "제목을 입력하세요.")
	private String title;
	
	@NotBlank(message = "내용을 입력하세요.")
	private String content;
	
	private String category;
}
