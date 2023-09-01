package com.example.Tax;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Tax.entity.UserEntity;
import com.example.Tax.repository.UserRepository;

@SpringBootTest
class TaxApplicationTests {

	@Autowired
	UserRepository userRepository;
	
	@Test
	void contextLoads() {
		UserEntity user = new UserEntity();
		user.setEmail("admin@tax.com");
		user.setTel("01012341234");
		user.setPassword("admin");
		
		userRepository.save(user);
	}

}
