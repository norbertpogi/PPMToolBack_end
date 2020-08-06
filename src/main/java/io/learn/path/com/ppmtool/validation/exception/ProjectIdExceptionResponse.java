package io.learn.path.com.ppmtool.validation.exception;

import lombok.Data;

@Data
public class ProjectIdExceptionResponse {
	
	private String projectIdentifier;

	public ProjectIdExceptionResponse(String projectIdentifier) {		
		this.projectIdentifier = projectIdentifier;
	}
	
	
}
