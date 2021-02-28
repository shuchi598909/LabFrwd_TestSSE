package com.laboratory.syetm.exception;

import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class LaboratoryGlobalExceptionHandler {

	@ExceptionHandler(value = {MappingException.class})
	 protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request){
		 String bodyOfResponse = "Missing required field";
	        return new ResponseEntity<Object>(bodyOfResponse, HttpStatus.BAD_REQUEST);
	}

}
