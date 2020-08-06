package io.learn.path.com.ppmtool.validation.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ValidationError {
	
	public ResponseEntity<?> MapValidationError(BindingResult result) {
		final Map<String, String> errorMap = new HashMap<>();
		if(result.hasErrors()) {
			result.getFieldErrors().forEach(n -> {
				errorMap.put(n.getField(), n.getDefaultMessage());				
			});
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
			
		}
		return null;
	}
	

}
