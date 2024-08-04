package com.employee.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
		Map<String,String> errors= new HashMap<String,String>();
		
		ex.getBindingResult().getAllErrors().forEach(error->{
			String fieldName = ((FieldError) error).getField();
		 	String errorMessage=error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Validation failed");
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setErrors(errors); 
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleEmployeeNotFound(ResourceNotFoundException ex){
		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		
	}

	
	   @ExceptionHandler(DuplicateResourceException.class)
	    @ResponseStatus(HttpStatus.CONFLICT)
	    public ResponseEntity<ErrorResponse> handleDuplicateResource(DuplicateResourceException ex) {
	        ErrorResponse errorResponse = new ErrorResponse();
	        Map<String,String> errors= new HashMap<String,String>();
	     //   errors.put("EmailId", ex.);
	        errorResponse.setMessage(ex.getMessage());
	        errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
	        errorResponse.setErrors(errors); 
	        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	    }

	    @ExceptionHandler(Exception.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setMessage("An unexpected error occurred");
	        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
