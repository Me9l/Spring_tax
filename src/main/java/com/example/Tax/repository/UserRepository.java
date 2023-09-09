package com.example.Tax.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Tax.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmail(String email);

	@Query(
			"SELECT u FROM UserEntity u WHERE u.username LIKE %:keyword% or u.tel LIKE %:keyword%"
			)
	Page<UserEntity> findByKeyword(@Param("keyword") String keyword,Pageable pageable);
}
