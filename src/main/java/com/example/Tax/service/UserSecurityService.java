package com.example.Tax.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Tax.entity.UserEntity;
import com.example.Tax.exception.DataNotFoundException;
import com.example.Tax.repository.UserRepository;

import lombok.RequiredArgsConstructor;

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
		Optional<UserEntity> findUser = userRepository.findByEmail(userEntity.getEmail());
		if(findUser.isPresent()) {
			throw new IllegalStateException("가입된 회원입니다.");
		}
	}
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			Optional<UserEntity> user = userRepository.findByEmail(email);

			return User.builder()
					.username(user.get().getEmail())
					.password(user.get().getPassword())
					.roles(user.get().getRole().toString())
					.build();
		} catch (Exception e) {
			throw new UsernameNotFoundException(email);
		}
	}

	public boolean alreadyTakenEmail(String email) {
		Optional<UserEntity> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			return user != null;
		}
		return true;
	}
	
	// 모든 유저 조회
	public Page<UserEntity> getAllUsers(int page, String keyword) {
		List<Sort.Order> sort = new ArrayList<>();
		sort.add(Sort.Order.desc("regdate"));
		Pageable pageable = PageRequest.of(page, 5,Sort.by(sort));
		Page<UserEntity> users = userRepository.findAll(pageable);
		return users;
	}
	
	// email을 받아 db에서 조회
	public UserEntity getUser(String email) {
		
		Optional<UserEntity> _user = userRepository.findByEmail(email);
		if ( _user.isPresent() ) {
			return _user.get();
		} else {
			throw new DataNotFoundException("찾을수 없는 이메일 : " + email);
		}
	}
}
