package com.Bitts.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Bitts.service.fileService;
@Service
public class fileServiceImpl implements fileService {

	@Override
	public String uplodingImage(String path, MultipartFile file) throws IOException {
		// file name
		String name = file.getOriginalFilename();
		// abc.png

		// random String
		String randomId = UUID.randomUUID().toString();
		// add random string to filename to be unique 
		String newfilename = randomId.concat(name.substring(name.lastIndexOf(".")));

		// fullPath
		String filePath = path + File.separator + newfilename;

		// create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
//		 file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		

		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+File.separator+fileName;
		
		InputStream is=new FileInputStream(fullPath);
		 
		return is;
	}

}
