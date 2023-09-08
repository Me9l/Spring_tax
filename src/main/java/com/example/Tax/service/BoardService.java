package com.example.Tax.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Tax.entity.BoardEntity;
import com.example.Tax.entity.UserEntity;
import com.example.Tax.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	
	public void saveBoard(String title, String content, String category, UserEntity user) {
		BoardEntity board = new BoardEntity();
		
		board.setTitle(title);
		board.setContent(content);
		board.setCategory(category);
		board.setUser(user);
		board.setRegdate(LocalDateTime.now());
		
		boardRepository.save(board);
	}
	
	// paging 조회
	public Page<BoardEntity> getBoardList(int page, String keyword) {
		
		List<Sort.Order> sort = new ArrayList<>();
		sort.add(Sort.Order.desc("regdate"));
		Pageable pageable = PageRequest.of(page, 5,Sort.by(sort));
		Page<BoardEntity> board = boardRepository.findAllByKeyword(keyword, pageable);
		return board;
	}
	
	//board paging 조회
	public Page<BoardEntity> getBoardByCategory(int page, String category) {
		List<Sort.Order> sort = new ArrayList<>();
		sort.add(Sort.Order.desc("regdate"));
		Pageable pageable = PageRequest.of(page, 5,Sort.by(sort));
		Page<BoardEntity> board = boardRepository.findByCategory(category, pageable);
		System.out.println(board.getSize());
		return board;
	}
}
