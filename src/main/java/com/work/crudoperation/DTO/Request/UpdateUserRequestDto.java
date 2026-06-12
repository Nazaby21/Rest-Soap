package com.work.crudoperation.DTO.Request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {
    private String username;
    private String email;
    private String password;
}
