package io.learn.path.com.ppmtool.validation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectIdException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ProjectIdException(String message) {
		super(message);
		
	}
	
}
