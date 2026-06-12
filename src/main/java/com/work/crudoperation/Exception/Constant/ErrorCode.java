package com.work.crudoperation.Exception.Constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    RESOURCE_NOT_FOUND("ERR_404", "%s with id %s not found", HttpStatus.NOT_FOUND),
    RESOURCE_ALREADY_EXIST("ERR_409", "%s with %s %s already exist", HttpStatus.CONFLICT),
    BAD_REQUEST("ERR_400", "%s with %s %s already exist", HttpStatus.BAD_REQUEST);

    private final String errorCode;
    private final String message;
    private final HttpStatus status;
}
