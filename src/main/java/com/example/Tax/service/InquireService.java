package com.example.Tax.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	public Page<InquireEntity> getInquireList(int page,String keyword) {
		
		List<Sort.Order> sort = new ArrayList<>();
		sort.add(Sort.Order.desc("regdate"));
		Pageable pageable = PageRequest.of(page, 5,Sort.by(sort));
		Page<InquireEntity> inquire = inquireRepository.findByKeyword(keyword, pageable);

		return inquire;
	}
}
