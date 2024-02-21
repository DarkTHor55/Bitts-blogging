package com.Bitts.controller;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Bitts.configration.appConstants;
import com.Bitts.model.newPost;
import com.Bitts.payload.imageResponse;
import com.Bitts.payload.postResponse;
import com.Bitts.repository.UserRepos;
import com.Bitts.repository.categroyRepo;
import com.Bitts.repository.postRepo;
import com.Bitts.service.CategoryService;
import com.Bitts.service.UserService;
import com.Bitts.service.fileService;
import com.Bitts.service.postService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
@RestController
@RequestMapping

public class postController {
	@Autowired
	private UserService userservice;
	@Autowired 
	private UserRepos userRepos;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private categroyRepo categroyRepo;
	@Autowired
	private postService postservice;
	@Autowired
	public postRepo postRepo;
	
	@Autowired
	public fileService fileService;
	
	
	//postCreate
	@PostMapping("/u/{u_id}/c/{c_id}/")
	public ResponseEntity<newPost> createPost(@Valid @RequestBody newPost post,@PathVariable("u_id") Long u_id,@PathVariable("c_id") Integer c_id ){
		newPost po=this.postservice.createPost(post, u_id,c_id);
		return new ResponseEntity<newPost>(po,HttpStatus.CREATED);
	}
	
	//getAllPost
	@GetMapping("/allPost")
	public ResponseEntity<postResponse> allPost(@RequestParam(value="pageNumber",defaultValue = appConstants.PAGE_NUMBER,required = false)Integer pageNo,
			@RequestParam(value="pageSize",defaultValue = appConstants.PAGE_size,required =false )Integer pageSize,@RequestParam (value="SortBy",defaultValue = appConstants.SORT_By,required=false)String sortBy
			,@RequestParam(value="sortDir",defaultValue = appConstants.SORT_DIR
			 ,required =false )String sortDir){
	    postResponse pst= this.postservice.getAllPost(pageNo,pageSize,sortBy,sortDir);
		return new ResponseEntity<postResponse>(pst,HttpStatus.OK);
	}
	//getPostById
	@GetMapping("/post/{post_id}")
			public newPost postById(@Valid@PathVariable Integer post_id) throws Exception{
				return this.postservice.getById(post_id);
			}
	//getByCategory
	@GetMapping("/c/{c_id}")
	public List<newPost> getPostByCartegory(@Valid @PathVariable Long c_id){
		Integer c=(int)(long)c_id;
		return postservice.AllPostbyCategory(c);
	}
	//getByUser
	@GetMapping("/u/{u_id}")
	public List<newPost> getPostByUser(@Valid @PathVariable Long u_id){
		try {
			return postservice.allPostByUser(u_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//delete post
	@DeleteMapping("/delPost/{p_id}")
	public ResponseEntity<?> deletePost(@Valid @PathVariable Integer p_id) {
		boolean a=false;
		try {
			for (newPost po : postservice.AllPost()) {
				if(po.getPostId().equals(p_id)) {
					a=true;
					postservice.deletePost(p_id);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(a==true) {
			return new ResponseEntity(Map.of("Message","Post Delected Successfully"),HttpStatus.OK);
		}else {
			return new ResponseEntity(Map.of("Message","Post Not Found"),HttpStatus.OK);
		}
		
	}
	
	// update
	@PutMapping("/updatePost/{p_id}")
	public  ResponseEntity<newPost> updatePost(@Valid @PathVariable Integer p_id,@RequestBody newPost post) {
		boolean a=false;
		try {
			for (newPost po :postservice.AllPost()) {
				if(po.getPostId().equals(p_id)) {
					a=true;
					postservice.update(p_id, post);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(a==true) {
			return new ResponseEntity(Map.of("Message","Post Update Successfully"),HttpStatus.OK);
		}else {
			return new ResponseEntity(Map.of("Message","Post Not Found"),HttpStatus.BAD_REQUEST);
		}
		
	
	} 
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<newPost>>searchPostByTitle(@PathVariable("keyword")String keyword){
		
		List<newPost>result=this.postservice.searchPost(keyword);
		return new ResponseEntity<List<newPost>>(result,HttpStatus.OK);
	}
	//path
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<imageResponse>fileupload(@RequestParam("image")MultipartFile iamge,@PathVariable("postId")Integer postId) throws Exception{
		String filename;
		try {
			filename = this.fileService.uplodingImage(path, iamge);
			newPost po=postservice.getById(postId);
			po.setImageName(filename);
			System.out.println("1111111111111111111111111"+po);
			this.postservice.update(postId, po);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new imageResponse(null,"image  not uploaded due to server error !!"),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<>(new imageResponse(filename,"image is successfully uploaded !!"),HttpStatus.OK);
	}
	@GetMapping(value="/images/{imagename}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imagename")String imageName,HttpServletResponse response)throws IOException {
		try {
			InputStream resource= this.fileService.getResource(path, imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(resource,response.getOutputStream());
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			 new ResponseEntity<ErrorMessage>(new ErrorMessage("Not Found !!"),HttpStatus.BAD_REQUEST);

		}
	}
	
}
