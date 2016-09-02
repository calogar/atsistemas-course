package com.at.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.at.library.dto.ErrorDTO;
import com.at.library.exceptions.UserNotFoundException;

@ControllerAdvice ( basePackages = { "com.at.library.controller" } )
public class ControllerFailures {
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO error(UserNotFoundException e){
		return new ErrorDTO(404, e.getMessage());
	}

	
}
