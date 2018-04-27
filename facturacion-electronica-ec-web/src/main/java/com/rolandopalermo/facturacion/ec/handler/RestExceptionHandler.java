package com.rolandopalermo.facturacion.ec.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rolandopalermo.facturacion.ec.common.exception.BadRequestException;
import com.rolandopalermo.facturacion.ec.common.exception.InternalServerException;
import com.rolandopalermo.facturacion.ec.common.exception.ResourceNotFoundException;
import com.rolandopalermo.facturacion.ec.dto.error.ErrorDetailDTO;
import com.rolandopalermo.facturacion.ec.dto.error.ValidationErrorDTO;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
		return new ResponseEntity<>(makeErrorDetail(ex, HttpStatus.NOT_FOUND, "Error interno"), null,
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ InternalServerException.class })
	public ResponseEntity<?> handleInternalServerException(InternalServerException ex, HttpServletRequest request) {
		return new ResponseEntity<>(makeErrorDetail(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Error interno"), null,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
		return new ResponseEntity<>(
				makeErrorDetail(ex, HttpStatus.BAD_REQUEST, "Error al ejecutar la petición"), null,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request) {
		ErrorDetailDTO errorDetail = new ErrorDetailDTO();
		// Populate errorDetail instance
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
		String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestPath == null) {
			requestPath = request.getRequestURI();
		}
		errorDetail.setTitle("Error de validación");
		errorDetail.setDetail("La petición no contiene toda la información requerida.");
		errorDetail.setDeveloperMessage(manve.getClass().getName());
		// Create ValidationError instances
		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
		for (FieldError fe : fieldErrors) {
			List<ValidationErrorDTO> validationErrorList = errorDetail.getErrors().get(fe.getField());
			if (validationErrorList == null) {
				validationErrorList = new ArrayList<ValidationErrorDTO>();
				errorDetail.getErrors().put(fe.getField(), validationErrorList);
			}
			ValidationErrorDTO validationError = new ValidationErrorDTO();
			validationError.setCode(fe.getCode());
			validationError.setMessage(fe.getDefaultMessage());
			validationErrorList.add(validationError);
		}
		return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
	}

	private ErrorDetailDTO makeErrorDetail(Exception ex, HttpStatus httpStatus, String title) {
		ErrorDetailDTO errorDetail = new ErrorDetailDTO();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(httpStatus.value());
		errorDetail.setTitle(title);
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());
		return errorDetail;
	}
}