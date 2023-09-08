//package com.example.Tax.service;
//
//import java.time.LocalDateTime;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.example.Tax.dto.UserForm;
//import com.example.Tax.entity.UserEntity;
//import com.example.Tax.repository.UserRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@Service
//public class UserService {
//
//	private final UserRepository userRepository;
//	private final PasswordEncoder passwordEncoder;
//	
//	public UserEntity saveUser(UserForm userForm) {
//		UserEntity userEntity = new UserEntity();
//		userEntity.setEmail(userForm.getEmail());
//		userEntity.setTel(userForm.getTel());
//		userEntity.setPassword(passwordEncoder.encode(userForm.getPassword1()));
//		userEntity.setPurpose(userForm.getPurpose());
//		LocalDateTime date = LocalDateTime.now();
//		userEntity.setRegdate(date);
//		
//		System.out.println(userEntity.getEmail());
//		System.out.println(userEntity.getTel());
//		System.out.println(userEntity.getPassword());
//		System.out.println(userEntity.getPurpose());
//		System.out.println(userEntity.getRegdate());
//		
//		userRepository.save(userEntity);
//		
//		return userEntity;
//	}
//	
//	public void selectUser(String email) {
//		Optional<UserEntity> _user = userRepository.findByuseremail(email);
//		
//		if ( _user.isPresent() ) {
//			UserEntity user = _user.get();
//		} else {
//			System.out.println("email not found");
//		}
//	}
	
//	public UserEntity getUser(String email) {
//		
//		Optional<UserEntity> _users = userRepository.findByemail(email);
//		if ( _users.isPresent() ) {
//			return _users.get();
//		} else throw new DataNotFoundException("존재하지 않는 이메일.");
//	}
//}