package com.Bitts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bitts.model.Category;

public interface categroyRepo extends JpaRepository<Category, Integer> {

}
