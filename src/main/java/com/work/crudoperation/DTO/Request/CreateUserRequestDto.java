package com.work.crudoperation.DTO.Request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {
    private String username;
    private String email;
    private String password;
}
