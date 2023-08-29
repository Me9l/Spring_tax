package com.example.Tax;

import java.time.LocalDateTime;

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
		user.setUserid("admin");
		user.setEmail("admin@tax.com");
		user.setTel("01012341234");
		user.setPassword("admin");
		user.setRegdate(LocalDateTime.now());
		
		userRepository.save(user);
	}

}
