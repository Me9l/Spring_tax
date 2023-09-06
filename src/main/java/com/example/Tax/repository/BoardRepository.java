package com.example.Tax.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Tax.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	List<BoardEntity> findByCategoryLikeOrderByRegdateDesc(String category);

	List<BoardEntity> findAllByOrderByRegdateDesc();

	@Query(
		"SELECT b FROM BoardEntity b WHERE b.title LIKE %:keyword% or b.content LIKE %:keyword% or b.category LIKE %:keyword% or b.id LIKE %:keyword%"
		)
	Page<BoardEntity> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
