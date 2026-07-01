package com.work.crudoperation.exception.custom;

import com.work.crudoperation.exception.constant.ErrorCode;
import lombok.Getter;


@Getter
public class BusinessException extends RuntimeException {

//    private final ErrorCode errorCode;
    private final String code;
    private final Integer status;


//    public BusinessException(ErrorCode errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    public static BusinessException byId(String resourceName, Object id) {
//        return new BusinessException(
//                ErrorCode.RESOURCE_NOT_FOUND
//        );
//    }

    public BusinessException(
            String code,
            Integer status
    ){

        super(code);

        this.code = code;
        this.status = status;
    }

}
