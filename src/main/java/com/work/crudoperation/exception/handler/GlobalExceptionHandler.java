package com.work.crudoperation.exception.handler;

import com.work.crudoperation.exception.constant.ErrorCode;
import com.work.crudoperation.exception.custom.BusinessException;
import com.work.crudoperation.exception.custom.ResourceAlreadyExistException;
import com.work.crudoperation.exception.custom.ResourceNotFoundException;
import com.work.crudoperation.exception.dto.ErrorResponse;
import com.work.crudoperation.utils.cache.ErrorMessageServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorMessageServices errorMessageServices;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest httpServletRequest) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(errorCode.getErrorCode())
                .message(ex.getMessage())
                .status(errorCode.getStatus().toString())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistException(ResourceAlreadyExistException ex, HttpServletRequest httpServletRequest) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(errorCode.getErrorCode())
                .message(ex.getMessage())
                .status(errorCode.getStatus().toString())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        String template = errorMessageServices.getErrorMessage(ex.getMessage());

        String message = String.format(template);
        ErrorResponse response = ErrorResponse.builder()
                        .code(ex.getCode())
                        .message(message)
                        .status(ex.getStatus().toString())
                        .timestamp(LocalDateTime.now())
                        .build();
        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }


}
