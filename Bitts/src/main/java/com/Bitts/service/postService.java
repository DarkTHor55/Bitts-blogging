package com.Bitts.service;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.Bitts.excepction.ResourceNotFound;
import com.Bitts.model.Category;
import com.Bitts.model.User;
import com.Bitts.model.newPost;
import com.Bitts.payload.postResponse;
import com.Bitts.repository.UserRepos;
import com.Bitts.repository.categroyRepo;
import com.Bitts.repository.postRepo;

@Service
public class postService {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private postRepo postRepo;

	@Autowired
	private UserRepos userRepo;
	@Autowired
	private categroyRepo categroyRepo;

	// create
	public newPost createPost(newPost post, Long u_id, int c_id) {
		Long c = (long) c_id;

		User user = this.userRepo.findById(u_id).orElseThrow(() -> new ResourceNotFound("User", "u_id", u_id));

		Category category = this.categroyRepo.findById(c_id)
				.orElseThrow(() -> new ResourceNotFound("Category", "c_id", c));

		newPost nPost = this.mapper.map(post, newPost.class);
		nPost.setContent(post.getContent());
		nPost.setImageName("default.png");
		nPost.setAddDate(new Date());
		nPost.setCategory(category);
		nPost.setUser(user);

		newPost postt = this.postRepo.save(nPost);
		return this.mapper.map(postt, newPost.class);
	}

	// get all post
	public postResponse getAllPost(Integer pageNo,Integer pageSize,String sortby,String sortDir) {
		
		Sort sort=(sortDir.equalsIgnoreCase("desc")?Sort.by(sortby).descending():Sort.by(sortby).ascending());

//		Sort sort=null;
//		
//		if(sortDir.equalsIgnoreCase("asc")) {
//				sort =Sort.by(sortby).ascending();
//			
//		}else  {
//			sort =Sort.by(sortby).descending();
//		}
		Pageable p=PageRequest.of(pageNo, pageSize,sort);
		Page<newPost>pagePost=postRepo.findAll(p);
		List<newPost> allpost=pagePost.getContent();
		
		postResponse postResponse=new postResponse();
		postResponse.setContent(allpost);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotelElement(pagePost.getTotalPages());
		postResponse.setTotlePage(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	public List<newPost> AllPost() {
		return postRepo.findAll();
	}

	// getById
	public newPost getById(Integer id) throws Exception {

		 Optional<newPost> post = postRepo.findById(id);
		  if (post.isPresent()) {
		        return post.get(); // Extract and return the post if it exists
		    } else {
		        // Throw an exception or handle the case when the post is not found
		        throw new Exception("Post not found with id: " + id);
		    }
		 

	}

	// getAll Post by category
	public List<newPost> AllPostbyCategory(Integer categoryId) {
		Category cat = this.categroyRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFound("Category", "categoryId", (long) categoryId));// retrive id from
																										// database
		List<newPost> post = this.postRepo.findByCategory(cat);// findBycategory is use to retrive all post which in
																// category
		return post;

	}

	// getall post by user
	public List<newPost> allPostByUser(Long u_id) {
		User user = this.userRepo.findById(u_id).orElseThrow(() -> new ResourceNotFound("User", "u_id", u_id));
		List<newPost> post = this.postRepo.findByUser(user);

		return post;
	}

	// delete
	public void deletePost(Integer id) {
		postRepo.deleteById(id);
	}

	// update
	public newPost update(Integer id, newPost post) {
		boolean a = false;
		for (newPost pst : AllPost()) {
			if (pst.getPostId() == id) {
				deletePost(id);
				newPost po = new newPost();
				po.setPostId(post.getPostId());
				po.setTitle(post.getTitle());
				po.setContent(post.getContent());
				po.setImageName(post.getImageName());
				po.setAddDate(post.getAddDate());
				po.setCategory(post.getCategory());
				po.setUser(post.getUser());
				postRepo.save(po);
			}
		}
		return post;

	}
	// search post
	public List<newPost> searchPost(String keyword){
		return this.postRepo.findByTitleContaining(keyword);
	}

}
