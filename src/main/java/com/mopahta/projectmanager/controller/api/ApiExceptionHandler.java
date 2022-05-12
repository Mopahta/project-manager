package com.mopahta.projectmanager.controller.api;

import com.mopahta.projectmanager.dto.ApiError;
import com.mopahta.projectmanager.exception.InvalidValuesException;
import com.mopahta.projectmanager.exception.NotFoundException;
import com.mopahta.projectmanager.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleException(UserAlreadyExistsException e) {
        ApiError error = new ApiError(HttpStatus.CONFLICT, e.getMessage());
        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    @ExceptionHandler(InvalidValuesException.class)
    public ResponseEntity<ApiError> handleException(InvalidValuesException e) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleException(NotFoundException e) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(error, error.getHttpStatus());
    }
}
