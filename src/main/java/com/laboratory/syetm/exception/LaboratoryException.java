package com.laboratory.syetm.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class LaboratoryException extends RuntimeException{

	private HttpStatus httpStatus;
	
	public LaboratoryException(String message) {
		super(message);
	}
	
	public LaboratoryException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
}
