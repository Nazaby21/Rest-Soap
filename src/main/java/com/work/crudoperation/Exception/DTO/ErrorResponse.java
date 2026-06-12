package com.work.crudoperation.Exception.DTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private String code;
    private String message;
    private String status;
    private LocalDateTime timestamp;
}
