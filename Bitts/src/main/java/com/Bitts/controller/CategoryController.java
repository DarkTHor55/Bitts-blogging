package com.Bitts.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bitts.model.Category;
import com.Bitts.repository.UserRepos;
import com.Bitts.repository.categroyRepo;
import com.Bitts.repository.postRepo;
import com.Bitts.service.CategoryService;
import com.Bitts.service.UserService;
import com.Bitts.service.postService;

import jakarta.validation.Valid;
@RestController
@RequestMapping

public class CategoryController {
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
	
	@PostMapping("/createCategory")
	public ResponseEntity<Category>createCategory(@Valid @RequestBody Category category){
		Category newCategory= categoryService.CreteCategory(category);
		return new ResponseEntity<Category>(newCategory,HttpStatus.CREATED);
	}
//	//put -update Category
		@PutMapping("/UpdateCategory/{CategoryId}")
		public ResponseEntity<Category> updateCategory(@Valid @PathVariable("CategoryId") int cateId,@RequestBody Category category){
			
			Category updateCate =this.categoryService.updateCategory(cateId, category);
			return ResponseEntity.ok(updateCate);
			}
		@DeleteMapping("/deleteCategory/{CategoryId}")
		public ResponseEntity<Category>delCategory(@PathVariable("CategoryId") int id){
			
			boolean t=false;
			for (Category cat : categoryService.getallCategory()) {
				if((int)cat.getC_id()==id) {
					
					System.out.println("milgya......................");
					Category delCategory=categoryService.deleteCategory(id);
					return new ResponseEntity(Map.of("Message","Category Delected Successfully"),HttpStatus.OK);
				}
				System.out.println(cat.getC_id()+"k"+id);
				
			}
			return new ResponseEntity(Map.of("Message","ID not present "),HttpStatus.OK);
			
		}
		@GetMapping("/getAllCategory")
		public ResponseEntity<List<Category>> getallCategory(){
			return ResponseEntity.ok(this.categoryService.getallCategory());
		}
		@GetMapping("/GetCategoryById/{CategoryId}")
		public Category getCategoryById( @PathVariable("CategoryId") int CateId){
			
			return categoryService.getCategoryById(CateId);
		}


}
