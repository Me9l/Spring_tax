package com.example.Tax.service;

import java.time.LocalDateTime;

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
}
