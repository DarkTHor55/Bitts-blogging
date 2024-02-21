package com.Bitts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.*;

@Entity
public class comments {
	@Id
	private int id;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	 private newPost newpost;
//	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public newPost getNewPost() {
		return newpost;
	}
	public void setNewPost(newPost newPost) {
		this.newpost = newPost;
	}
	public comments(int id, String content, User user, newPost newPost) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
		this.newpost = newPost;
	}
	public comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
