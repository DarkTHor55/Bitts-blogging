package com.Bitts.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name="Post")
public class newPost {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	@NotBlank
	@Column(name="Post_Title",nullable = false)
	@Size(min =4,max=100,message = "minimun size is 4 character and maximun size is 100 character")
	private String title;
	@Column(name="Post_Content")
	@NotEmpty
	@Size(min=10,message="minimun size is 10 character")
	private String Content;
	@Column(name="Image")
	@NotEmpty
	
	private String imageName;
	@Column(name="Datee")
	private Date addDate;

	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy ="newpost",cascade = CascadeType.ALL )
	private Set<comments>comments=new HashSet<>();
//	
//	public Set<comments> getComments() {
//		return comments;
//	}
//	public void setComments(Set<comments> comments) {
//		this.comments = comments;
//	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public newPost(Integer postId,
			@NotBlank @Size(min = 4, max = 100, message = "minimun size is 4 character and maximun size is 100 character") String title,
			@NotBlank @Size(min = 10, message = "minimun size is 10 character") String content, String imageName,
			Date addDate) {
		super();
		this.postId = postId;
		this.title = title;
		Content = content;
		this.imageName = imageName;
		this.addDate = addDate;
	}
	public newPost() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
