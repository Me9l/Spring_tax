package com.example.Tax.service;

import org.springframework.stereotype.Service;

import com.example.Tax.entity.InquireEntity;
import com.example.Tax.entity.UserEntity;
import com.example.Tax.repository.InquireRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InquireService {

	private final InquireRepository inquireRepository;
	
	public InquireEntity saveInquire(InquireEntity inquireEntity) {
		return inquireRepository.save(inquireEntity);
	}
}
