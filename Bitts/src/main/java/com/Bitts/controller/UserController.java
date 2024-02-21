package com.Bitts.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Bitts.model.Category;
import com.Bitts.model.User;
import com.Bitts.model.newPost;
import com.Bitts.repository.UserRepos;
import com.Bitts.repository.categroyRepo;
import com.Bitts.repository.postRepo;
import com.Bitts.service.CategoryService;
import com.Bitts.service.UserService;
import com.Bitts.service.postService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class UserController {
	@Autowired
	private UserService userservice;
	@Autowired 
	private UserRepos userRepos;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private categroyRepo categroyRepo;
	@Autowired
	private postService postService;
	@Autowired
	public postRepo postRepo;
	
	
//post -create user 
	
	@PostMapping("/createUser")
	public ResponseEntity<User>createUser(@Valid @RequestBody User user){
		User newUser=userservice.createUser(user);
		return new ResponseEntity<>(newUser,HttpStatus.CREATED);
	}
	//put -update user
	@PutMapping("/UpdateUser/{UserId}")
	public ResponseEntity<User> Updateuser(@Valid @PathVariable("UserId") Long UserId,@RequestBody User user){
		User updateUser =this.userservice.UpdateUser(UserId, user);
		return ResponseEntity.ok(updateUser);
	}
	@DeleteMapping("/deleteUser/{UserId}")
	public ResponseEntity<?>delUser(@PathVariable("UserId") Long id){
		User delUser=this.userservice.deleteUser(id);
		return new ResponseEntity(Map.of("Message","User Delected Successfully"),HttpStatus.OK);
	}
	@GetMapping("/getAllUSer")
	public ResponseEntity<List<User>> getallUser(){
		return ResponseEntity.ok(this.userservice.GetAllUser());
	}
	@GetMapping("/GetUSerById/{UserId}")
	public User getallUser( @PathVariable Long UserId){
		
		return userservice.getUserById(UserId);
	}
	
	
		
}
