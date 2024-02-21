//package com.Bitts.controller;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.Bitts.payload.imageResponse;
//import com.Bitts.service.fileService;
//import com.Bitts.service.postService;
//
//import jakarta.servlet.http.HttpServletResponse;
//
//@RequestMapping
//@RestController
//public class fileController {
//	@Autowired
//	private postService postService;
//	@Autowired
//	private fileService fileservice;
//	@Value("${project.image}")
//	private String path;
//	
//	@PostMapping("/upload")
//	public ResponseEntity<imageResponse>fileupload(@RequestParam("image")MultipartFile iamge){
//		String filename;
//		try {
//			filename = this.fileservice.uplodingImage(path, iamge);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(new imageResponse(null,"image  not uploaded due to server error !!"),HttpStatus.INTERNAL_SERVER_ERROR);
//			
//		}
//		return new ResponseEntity<>(new imageResponse(filename,"image is successfully uploaded !!"),HttpStatus.OK);
//	}
//	@GetMapping(value="/images/{imagename}",produces = MediaType.IMAGE_JPEG_VALUE)
//	public void downloadImage(@PathVariable("imagename")String imageName,HttpServletResponse response)throws IOException {
//		try {
//			InputStream resource= this.fileservice.getResource(path, imageName);
//			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//			StreamUtils.copy(resource,response.getOutputStream());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
