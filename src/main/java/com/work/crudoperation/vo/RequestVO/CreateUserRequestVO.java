package com.work.crudoperation.vo.RequestVO;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestVO(
        @NotBlank(message = "username is require")
        String username,
        @NotBlank(message = "email is require")
        String email,
        @NotBlank(message = "password is require")
        String password
) {
}
