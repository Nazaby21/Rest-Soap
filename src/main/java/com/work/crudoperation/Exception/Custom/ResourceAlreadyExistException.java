package com.work.crudoperation.Exception.Custom;

import com.work.crudoperation.Exception.Constant.ErrorCode;
import lombok.Getter;

@Getter
public class ResourceAlreadyExistException extends RuntimeException{
    private final ErrorCode errorCode;
    public ResourceAlreadyExistException(String message){
        super(message);
        this.errorCode = ErrorCode.RESOURCE_ALREADY_EXIST;
    }

    public static ResourceAlreadyExistException byField(String resourceName, String name, String email){
        return new ResourceAlreadyExistException(
                String.format(
                        ErrorCode.RESOURCE_ALREADY_EXIST.getMessage(),
                        resourceName,
                        name,
                        email
                )
        );
    }
}
