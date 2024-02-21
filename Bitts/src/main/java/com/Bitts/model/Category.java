package com.Bitts.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity

public class Category {
	@Id
	@NotNull
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int c_id;
	
	@NotBlank
	@Size(min=4,message = "Minimum size is 4 ")
	@Column(name="Title",length=100,nullable = false)
	private String categoryTitle;
	
	@Size(min=10,message = "Minimum size is 10")
	@NotEmpty
	@Column(name="Discription")
	private String categoryDescription;

	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL )// in mapping we define from which we have to link it //cascdetype.allis use if we change in child their prent will chnage vice versa
	private List<newPost> Posts=new ArrayList<>();
	
	public Category(int c_id, String categoryTitle, String categoryDescription) {
		super();
		this.c_id = c_id;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
	 
}
