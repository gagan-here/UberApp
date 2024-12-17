package com.uber.uberapp.advices;

import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.exceptions.RunTimeConflictException;
import java.util.List;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<?>> handleResourceNotFound(
      ResourceNotFoundException exception) {
    ApiError apiError =
        ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
    return buildErrorResponseEntity(apiError);
  }

  @ExceptionHandler(RunTimeConflictException.class)
  public ResponseEntity<ApiResponse<?>> handleRunTimeConflictException(
      RunTimeConflictException exception) {
    ApiError apiError =
        ApiError.builder().status(HttpStatus.CONFLICT).message(exception.getMessage()).build();
    return buildErrorResponseEntity(apiError);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception) {
    ApiError apiError =
        ApiError.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .message(exception.getMessage())
            .build();
    return buildErrorResponseEntity(apiError);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<?>> handleInputValidationErrors(
      MethodArgumentNotValidException exception) {
    List<String> errors =
        exception.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .toList();

    ApiError apiError =
        ApiError.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message("Input validation failed")
            .subErrors(errors)
            .build();
    return buildErrorResponseEntity(apiError);
  }

  private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
  }
}