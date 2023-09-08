package com.example.Tax;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.Tax.entity.BoardEntity;
import com.example.Tax.entity.UserEntity;
import com.example.Tax.repository.BoardRepository;
import com.example.Tax.repository.UserRepository;

@SpringBootTest
class TaxApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Autowired
	BoardRepository boardRepository;
	
//	@Test
	void contextLoads() {
		UserEntity user = new UserEntity();
		user.setEmail("admin@tax.com");
		user.setTel("01012341234");
		user.setPassword("admin");
		
		userRepository.save(user);
	}
	@Test
	void getBoardByCategory() {
		List<Sort.Order> sort = new ArrayList<>();
		sort.add(Sort.Order.desc("regdate"));
		Pageable pageable = PageRequest.of(0, 5,Sort.by(sort));
	 Page<BoardEntity> board = boardRepository.findByCategory("news", pageable);
	 System.out.println(board.getSize());
	 
	}
}
