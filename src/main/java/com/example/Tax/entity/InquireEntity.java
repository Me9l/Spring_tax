package com.example.Tax.entity;

import java.time.LocalDateTime;

import com.example.Tax.dto.InquireForm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "inquire")
public class InquireEntity extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String tel;

	private String email;
	
	private String purpose;
	
	private String content;

	private String sectors;
	
	private boolean optin;
	
	private boolean settle;
	
	public static InquireEntity createInquire(InquireForm inquireForm) {
		InquireEntity inquire = new InquireEntity();
		inquire.setUsername(inquireForm.getUsername());
		inquire.setTel(inquireForm.getTel());
		inquire.setEmail(inquireForm.getEmail());
		inquire.setPurpose(inquireForm.getPurpose());
		inquire.setContent(inquireForm.getContent());
		inquire.setSectors(inquireForm.getSectors());
		inquire.setOptin(inquireForm.isOptin());
		inquire.setRegdate(LocalDateTime.now());
		
		return inquire;
	}
}
