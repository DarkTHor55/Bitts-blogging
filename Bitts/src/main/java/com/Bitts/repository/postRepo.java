package com.Bitts.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.Bitts.model.Category;
import com.Bitts.model.User;
import com.Bitts.model.newPost;

public interface postRepo  extends JpaRepository<newPost, Integer> {
	
	List<newPost> findByUser(User user);
	List<newPost> findByCategory(Category category);
	List<newPost> findByTitleContaining(String title);

}
