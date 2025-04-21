package sg.edu.nus.team3.shoppingcart.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Dion Yao
 * 
 */

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)	
	public ResponseEntity<?> loginException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach((error) -> {
			String fieldName = error.getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(DataIntegrityViolationException.class)	
	public ResponseEntity<?> duplicateEmailException(DataIntegrityViolationException ex) {

		Map<String, String> errors = new HashMap<>();

		String fieldName = "duplicate";
		String errorMessage = "An account associated with your email already exists";

		errors.put(fieldName, errorMessage);

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
