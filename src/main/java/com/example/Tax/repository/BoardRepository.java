package com.example.Tax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tax.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	List<BoardEntity> findByCategoryLikeOrderByRegdateDesc(String category);
}
