package com.work.crudoperation.Exception.Handler;

import com.work.crudoperation.Exception.Constant.ErrorCode;
import com.work.crudoperation.Exception.Custom.ResourceAlreadyExistException;
import com.work.crudoperation.Exception.Custom.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.work.crudoperation.Exception.DTO.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest httpServletRequest){
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
    public ResponseEntity<ErrorResponse>handleResourceAlreadyExistException(ResourceAlreadyExistException ex, HttpServletRequest httpServletRequest){
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
}
