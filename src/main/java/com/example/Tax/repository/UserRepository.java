package com.example.Tax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tax.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	
}
