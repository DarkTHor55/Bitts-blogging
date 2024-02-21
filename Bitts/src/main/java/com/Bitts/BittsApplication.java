package com.Bitts;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Bitts.configration.PassEnoder;

@SpringBootApplication
//public class BittsApplication implements CommandLineRunner {
public class BittsApplication{
	@Autowired
	private PasswordEncoder passEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BittsApplication.class, args);
	}

	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


//	@Override
//	public void run(String... args) throws Exception {
//System.out.println(this.passEncoder.encode("123456789"));		
//	}
}
