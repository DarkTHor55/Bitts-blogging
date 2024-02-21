package com.Bitts.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.Bitts.excepction.ResourceNotFound;
import com.Bitts.model.Category;
import com.Bitts.model.User;
import com.Bitts.repository.*;

import jakarta.validation.Valid;

@Service
public class CategoryService {
	@Autowired
	private ModelMapper mapper ;
	
	@Autowired
	private categroyRepo categroyRepo;
	
	@Autowired
	public CategoryService(categroyRepo categroyRepo) {
		this.categroyRepo=categroyRepo;
	}
	//getById
	public Category getCategoryById(int id) {
		
		Category catId=this.categroyRepo.findById(id).orElseThrow(()->new ResourceNotFound("Category", "Id", (long) (id)));
		return catId;
	}
	
		//getAll
		public List<Category >getallCategory() {
			return categroyRepo.findAll();
		}
	
//create'
	public Category CreteCategory(Category category) {
	Category newCategory=this.mapper.map(category, Category.class);
		return  this.categroyRepo.save(newCategory);
	}
	//delete
	
	public Category deleteCategory(int id) {
		Optional<Category>u=categroyRepo.findById(id);
		if(u.isEmpty()) {
			System.out.println("Not found");
		}
		 this.categroyRepo.deleteById(id);
		 return null;
	}
	
	//upadte
	public Category updateCategory(int id ,Category category) {
		for (Category cat: getallCategory()) {
			if(cat.getC_id()==id) {
				deleteCategory(id);
				Category newCate=new Category();
				newCate.setC_id(id);
				newCate.setCategoryTitle(category.getCategoryTitle());
				newCate.setCategoryDescription(category.getCategoryDescription());
				return categroyRepo.save(newCate);
				
			}
		}
		return category;
	}
	
	
	
}
