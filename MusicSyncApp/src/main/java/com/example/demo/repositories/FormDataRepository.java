package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.FormData;

public interface FormDataRepository extends JpaRepository<FormData, Long> {
    
}

