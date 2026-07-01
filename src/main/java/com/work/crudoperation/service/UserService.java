package com.work.crudoperation.service;

import com.work.crudoperation.dto.Request.CreateUserRequestDto;
import com.work.crudoperation.dto.Request.UpdateUserRequestDto;
import com.work.crudoperation.dto.Response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(CreateUserRequestDto createUserRequestDto);

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getUsers();

    UserResponseDto updateUser(Long id, UpdateUserRequestDto updateUserRequestDto);

    void deleteUser(Long id);
}
