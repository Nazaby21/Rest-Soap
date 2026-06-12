package com.work.crudoperation.Exception.Custom;

import com.work.crudoperation.Exception.Constant.ErrorCode;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;
    public ResourceNotFoundException(String message){
        super(message);
        this.errorCode = ErrorCode.RESOURCE_NOT_FOUND;
    }

    public static ResourceNotFoundException byId(String resourceName, Object id){
        return new ResourceNotFoundException(
                String.format(
                        ErrorCode.RESOURCE_NOT_FOUND.getMessage(),
                        resourceName,
                        id
                )
        );
    }

}
