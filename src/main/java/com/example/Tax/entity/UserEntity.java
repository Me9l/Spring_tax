package com.example.Tax.entity;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Tax.constant.UserRole;
import com.example.Tax.dto.UserForm;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "users")
@ToString
public class UserEntity extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(unique = true)
	private String email;
	
	private String username;
	
	private String password;
	
	@Column(unique = true)
	private String tel;

	private String purpose;

	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	public static UserEntity createUser(UserForm userForm, PasswordEncoder passwordEncoder) {
		UserEntity user = new UserEntity();
		user.setEmail(userForm.getEmail());
		user.setUsername(userForm.getUsername());
		user.setPassword(passwordEncoder.encode(userForm.getPassword1()));
		user.setTel(userForm.getTel());
		user.setPurpose(userForm.getPurpose());
		user.setRegdate(LocalDateTime.now());
		user.setRole(UserRole.USER);
		return user;
	}
}
