package com.alligator.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
																  @NonNull final HttpHeaders headers,
																  @NonNull final HttpStatusCode status,
																  @NonNull final WebRequest request) {
		final List<String> errors = new ArrayList<>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, errors);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex,
														@NonNull final HttpHeaders headers,
														@NonNull final HttpStatusCode status,
														@NonNull final WebRequest request) {
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();
		return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, Collections.singletonList(error));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
																	 @NonNull final HttpHeaders headers,
																	 @NonNull final HttpStatusCode status,
																	 @NonNull final WebRequest request) {
		final String error = ex.getRequestPartName() + " part is missing";
		return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, Collections.singletonList(error));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex,
																		  @NonNull final HttpHeaders headers,
																		  @NonNull final HttpStatusCode status,
																		  @NonNull final WebRequest request) {
		final String error = ex.getParameterName() + " parameter is missing";
		return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, Collections.singletonList(error));
	}


	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex) {
		final String error = ex.getName() + " should be of type " +
				(ex.getRequiredType() != null ? ex.getRequiredType().getName() : "null");
		return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, Collections.singletonList(error));
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex) {
		final List<String> errors = new ArrayList<>();
		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
		}
		return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, errors);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
																   @NonNull final HttpHeaders headers,
																   @NonNull final HttpStatusCode status,
																   @NonNull final WebRequest request) {
		final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
		return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList(error));
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex,
																		 @NonNull final HttpHeaders headers,
																		 @NonNull final HttpStatusCode status,
																		 @NonNull final WebRequest request) {
		final StringBuilder error = new StringBuilder();
		error.append(ex.getMethod());
		error.append(" method is not supported for this request. Supported methods are ");
		Optional.ofNullable(ex.getSupportedHttpMethods())
				.ifPresent(methods -> methods.forEach(t -> error.append(t).append(" ")));
		return handleExceptionInternal(ex, HttpStatus.METHOD_NOT_ALLOWED, Collections.singletonList(error.toString()));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
																	 @NonNull final HttpHeaders headers,
																	 @NonNull final HttpStatusCode status,
																	 @NonNull final WebRequest request) {
		final StringBuilder error = new StringBuilder();
		error.append(ex.getContentType());
		error.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> error.append(t).append(" "));
		return handleExceptionInternal(ex, HttpStatus.UNSUPPORTED_MEDIA_TYPE, Collections.singletonList(error.toString()));
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleAll(final Exception ex) {
		return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList("error occurred"));
	}

	private ResponseEntity<Object> handleExceptionInternal(final Exception ex, HttpStatus httpStatus, final List<String> error) {
		logger.info(ex.getClass().getName());
		logger.error(ex.getLocalizedMessage(), ex);
		final ApiError apiError = new ApiError(httpStatus, ex.getLocalizedMessage(), error);
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}