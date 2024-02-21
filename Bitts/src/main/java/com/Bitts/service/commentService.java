package com.Bitts.service;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bitts.excepction.ResourceNotFound;
import com.Bitts.model.Category;
import com.Bitts.model.User;
import com.Bitts.model.comments;
import com.Bitts.model.newPost;
import com.Bitts.repository.UserRepos;
import com.Bitts.repository.commentsRepo;
import com.Bitts.repository.postRepo;

@Service
public class commentService {
	@Autowired
	private commentsRepo commentsRepo;

	@Autowired
	private UserRepos userrepos;
	@Autowired
	private postRepo postRepo;

	@Autowired
	private ModelMapper mapper;

	// create comment
	public comments createComment(comments comment, Long userId, Integer postId) {
		User user = this.userrepos.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "User Id", userId));
		newPost post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFound("postId", "post Id", (long) postId));
		comments newComments = mapper.map(comment, comments.class);
		newComments.setUser(user);
		newComments.setNewPost(post);
		System.out.println("..................");
//		
		return this.commentsRepo.save(newComments);
	}

	// delete comment
	public void deleteComment(int comment_id) {
		commentsRepo.findById(comment_id)
				.orElseThrow(() -> new ResourceNotFound("comments", "commentId", (long) comment_id));
		commentsRepo.deleteById(comment_id);

	}

	// update comment
	public comments updateComment(comments comments, int commentId) {
		User user = comments.getUser();
		newPost post = comments.getNewPost();
		commentsRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFound("comments", "commentId", (long) commentId));
		commentsRepo.deleteById(commentId);
		comments newComments = mapper.map(comments, comments.class);
		newComments.setNewPost(post);
		newComments.setUser(user);
		commentsRepo.save(newComments);
		return newComments;
	}

	// all coment
	public List<comments> getAllComment() {
		return commentsRepo.findAll();
	}

	// get by comment id
	public comments getById(int id) {

		comments cd = this.commentsRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Category", "Id", (long) (id)));
		return cd;
	}
	

}
