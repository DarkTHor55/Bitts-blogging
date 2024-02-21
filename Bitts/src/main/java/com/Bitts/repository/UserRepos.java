package com.Bitts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bitts.model.User;

public interface UserRepos extends JpaRepository<User, Long>{
	Optional<User>findByEmail(String email);

}
