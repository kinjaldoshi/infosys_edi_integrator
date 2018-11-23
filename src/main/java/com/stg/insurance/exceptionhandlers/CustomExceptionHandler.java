package com.stg.insurance.exceptionhandlers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.stg.insurance.constants.ErrorConstants;
import com.stg.insurance.models.errors.ErrorResource;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ErrorResource> handleIOException(IOException e) {
		ErrorResource errorResource = new ErrorResource();
		errorResource.setType(ErrorConstants.TYPE);
		errorResource.setCode(ErrorConstants.INTERNAL_SERVER_ERROR);
		errorResource.setMessage(e.getMessage());
		errorResource.setDetail(ErrorConstants.IO_EXCEPTION_MESSAGE);
		return new ResponseEntity<>(errorResource, HttpStatus.valueOf(ErrorConstants.INTERNAL_SERVER_ERROR));
	}
}
