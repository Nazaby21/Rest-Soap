package com.work.crudoperation.Service;

import com.work.crudoperation.DTO.Request.CreateUserRequestDto;
import com.work.crudoperation.DTO.Request.UpdateUserRequestDto;
import com.work.crudoperation.DTO.Response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(CreateUserRequestDto createUserRequestDto);
    UserResponseDto getUserById(Long id);
    List<UserResponseDto> getUsers();
    UserResponseDto updateUser(Long id, UpdateUserRequestDto updateUserRequestDto);
    void deleteUser(Long id);
}
