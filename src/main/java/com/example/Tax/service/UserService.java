package com.example.Tax.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Tax.entity.UserEntity;
import com.example.Tax.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	
	public UserEntity create(String userid, String password, String email) {
		UserEntity user = new UserEntity();
		user.setUserid(userid);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);
		LocalDateTime date = LocalDateTime.now();
		user.setRegdate(date);
		
		userRepository.save(user);
		
		return user;
		
	}
}