package com.alligator.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", ex);
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error", ex);
		apiError.setErrors(ex.getConstraintViolations().stream()
				.map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
				.collect(Collectors.toList()));
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
		if (ex.getCause() instanceof ConstraintViolationException) {
			return handleConstraintViolation((ConstraintViolationException) ex.getCause(), request);
		}
		ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Database error", ex.getCause());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, "Access denied", ex);
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMaxUploadSizeExceededException(@NonNull MaxUploadSizeExceededException ex,
																		  @NonNull HttpHeaders headers,
																		  @NonNull HttpStatusCode status,
																		  @NonNull WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.PAYLOAD_TOO_LARGE, "File size exceeds limit", ex);
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(@NonNull HttpRequestMethodNotSupportedException ex,
																		 @NonNull HttpHeaders headers,
																		 @NonNull HttpStatusCode status,
																		 @NonNull WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(), ex);
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(@NonNull MissingServletRequestParameterException ex,
																		  @NonNull HttpHeaders headers,
																		  @NonNull HttpStatusCode status,
																		  @NonNull WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(@NonNull MissingPathVariableException ex,
															   @NonNull HttpHeaders headers,
															   @NonNull HttpStatusCode status,
															   @NonNull WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  @NonNull HttpHeaders headers,
																  @NonNull HttpStatusCode status,
																  @NonNull WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Validation error");
		apiError.setErrors(ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
				.collect(Collectors.toList()));
		apiError.setErrors(ex.getBindingResult().getGlobalErrors().stream()
				.map(globalError -> globalError.getObjectName() + ": " + globalError.getDefaultMessage())
				.collect(Collectors.toList()));
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
