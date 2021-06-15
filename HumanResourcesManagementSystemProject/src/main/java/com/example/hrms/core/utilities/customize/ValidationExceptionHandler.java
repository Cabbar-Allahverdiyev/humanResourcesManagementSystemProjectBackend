package com.example.hrms.core.utilities.customize;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.hrms.core.utilities.results.ErrorDataResult;

@ControllerAdvice
public class ValidationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception){
		ValidationError validationError = new ValidationError
				(false, "Təsdiqləmə xətası", exception.getBindingResult().getFieldError().getDefaultMessage());
		
		
		return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
		
		
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public ErrorDataResult<Object> handleValidationException
//	(MethodArgumentNotValidException exceptions){
//		Map<String,String> validationErrors = new HashMap<String, String>();
//		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
//			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
//		}
//		
//		ErrorDataResult<Object> errors 
//		= new ErrorDataResult<Object>(validationErrors,"Təsdiqləmə xətaları");
//		return errors;
	//}
}

