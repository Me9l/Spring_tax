package com.example.Tax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tax.entity.InquireEntity;

public interface InquireRepository extends JpaRepository<InquireEntity, Long> {

}
