package com.example.Tax.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Tax.entity.InquireEntity;

public interface InquireRepository extends JpaRepository<InquireEntity, Long> {

	@Query(
	"SELECT i FROM InquireEntity i WHERE i.tel LIKE %:keyword% or i.email LIKE %:keyword% or i.purpose LIKE %:keyword% or i.content LIKE %:keyword%"
			)
	Page<InquireEntity> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
