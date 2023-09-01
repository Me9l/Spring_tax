package com.example.Tax.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Tax.entity.UserEntity;
import com.example.Tax.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Transactional
@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	public UserEntity saveUser(UserEntity userEntity) {
		validateDuplicateUser(userEntity);
		return userRepository.save(userEntity);
	}

	private void validateDuplicateUser(UserEntity userEntity) {
		UserEntity findUser = userRepository.findByEmail(userEntity.getEmail());
		if(findUser != null) {
			throw new IllegalStateException("가입된 회원입니다.");
		}
	}
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			UserEntity user = userRepository.findByEmail(email);
			log.info("user.getEmail() : " + user.getEmail());
			return User.builder()
					.username(user.getEmail())
					.password(user.getPassword())
					.roles(user.getRole().toString())
					.build();
		} catch (Exception e) {
			throw new UsernameNotFoundException(email);
		}
	}
}
