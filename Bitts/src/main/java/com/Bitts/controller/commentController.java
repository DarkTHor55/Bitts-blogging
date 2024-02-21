package com.Bitts.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.Bitts.model.comments;
import com.Bitts.repository.UserRepos;
import com.Bitts.repository.categroyRepo;
import com.Bitts.repository.commentsRepo;
import com.Bitts.repository.postRepo;
import com.Bitts.service.CategoryService;
import com.Bitts.service.UserService;
import com.Bitts.service.commentService;
import com.Bitts.service.postService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class commentController {
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
	@Autowired
	public commentService commentService;
	
	@Autowired
	public commentsRepo commentsRepo;

	
	//create 
	@PostMapping("/createcomment/u/{u_id}/p/{p_id}/")
	public ResponseEntity<comments> createcomment(@Valid @RequestBody comments comment,@PathVariable("u_id") Long u_id,@PathVariable("p_id") Integer p_id){
		System.out.println("rrrrrrrrrrrr");
		comments createcom= this.commentService.createComment(comment,u_id, p_id);
		return new ResponseEntity<comments>(createcom,HttpStatus.CREATED);
	}
	//gett all comment
	@GetMapping("allComment")
	public ResponseEntity<List<comments>>allComment(){
		
		return new ResponseEntity<List<comments>>(commentService.getAllComment(),HttpStatus.OK);
	}
	//getById
	@GetMapping("/comment/{comment}")
	public comments getComment( @PathVariable("comment") int CId){
		
		return commentService.getById(CId);
	}
	//deldete
	@DeleteMapping("/deleteCom/{c_id}")
	public ResponseEntity<comments>delCategory(@PathVariable("c_id") int id){
		
		boolean t=false;
		for (comments cat : commentService.getAllComment()){
			if((int)cat.getId()==id) {
				
				System.out.println("milgya......................");
				commentService.deleteComment(id);
				return new ResponseEntity(Map.of("Message","Category Delected Successfully"),HttpStatus.OK);
			}
			System.out.println(cat.getId()+"k"+id);
			
		}
		return new ResponseEntity(Map.of("Message","ID not present "),HttpStatus.OK);
		
	}
	//update
	@PutMapping("/UpdateCommy/{c_id}")
	public ResponseEntity<comments> updateCategory(@Valid @PathVariable("c_id") int cateId,@RequestBody comments comments){
		
		comments updateCate =this.commentService.updateComment( comments,cateId);
		return ResponseEntity.ok(updateCate);
		}

}
