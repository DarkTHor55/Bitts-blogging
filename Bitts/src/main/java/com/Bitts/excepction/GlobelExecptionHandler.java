package com.Bitts.excepction;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobelExecptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class)
	public  ResponseEntity<Map<String ,String >>handleMethodVlaidException(MethodArgumentNotValidException ex){
		Map<String ,String>errors=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{   //here we fetching  all error  and handling those error
			String fieldName=	((FieldError)error).getField(); // error don't have field so we are typecasting it in fieldError
			String message=error.getDefaultMessage();
			errors.put(fieldName, message);
		});;
		
		return new ResponseEntity<Map<String ,String>>(errors,HttpStatus.BAD_REQUEST);
	}
}
