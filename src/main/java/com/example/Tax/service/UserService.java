package com.example.Tax.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Tax.dto.UserForm;
import com.example.Tax.entity.UserEntity;
import com.example.Tax.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserEntity saveUser(UserForm userForm) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserid(userForm.getUserid());
		userEntity.setUsername(userForm.getUsername());
		userEntity.setPassword(passwordEncoder.encode(userForm.getPassword1()));
		userEntity.setEmail(userForm.getEmail());
		userEntity.setPurpose(userForm.getPurpose());
		userRepository.save(userEntity);
		return userEntity;
	}
	
}