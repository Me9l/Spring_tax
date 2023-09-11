package com.example.Tax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Tax.service.UserSecurityService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class TaxApplication {

	private final UserSecurityService userSecurityService;
	
	public TaxApplication(UserSecurityService userSecurityService) {
		this.userSecurityService = userSecurityService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TaxApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		userSecurityService.createAdmin();
	}
}
