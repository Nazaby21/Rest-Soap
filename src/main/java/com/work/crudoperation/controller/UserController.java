package com.work.crudoperation.controller;

import com.work.crudoperation.dto.Request.CreateUserRequestDto;
import com.work.crudoperation.dto.Request.UpdateUserRequestDto;
import com.work.crudoperation.dto.Response.UserResponseDto;
import com.work.crudoperation.exception.dto.ApiResponse;
import com.work.crudoperation.mapper.UserMapper;
import com.work.crudoperation.service.UserService;
import com.work.crudoperation.soap.user.UserSoapClient;
import com.work.crudoperation.vo.RequestVO.CreateUserRequestVO;
import com.work.crudoperation.vo.RequestVO.UpdateUserRequestVO;
import com.work.crudoperation.vo.ResponseVO.UserResponseVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserSoapClient userSoapClient;

    @PostMapping
    public ApiResponse<UserResponseVO> createUser(@RequestBody CreateUserRequestVO createUserRequestVO) {
        CreateUserRequestDto createUserRequestDto = userMapper.createUserVoToUserDto(createUserRequestVO);
        UserResponseDto userResponseDto = userService.createUser(createUserRequestDto);
        UserResponseVO userResponseVO = userMapper.userDtoToUserVO(userResponseDto);
        return ApiResponse.success(userResponseVO);
    }

    @PostMapping("/soap")
    public String createUser(@RequestBody CreateUserRequestDto request) throws Exception {
        return userSoapClient.createUser(request);
    }

    @GetMapping()
    public ApiResponse<List<UserResponseVO>> getUsers() {
        List<UserResponseVO> users = userService.getUsers()
                .stream()
                .map(userMapper::userDtoToUserVO)
                .toList();
        return ApiResponse.success(users);
    }

    @GetMapping("/soap")
    public String getAllUsers() throws Exception {
        return userSoapClient.getAllUsers();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponseVO> getUserById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        UserResponseVO userResponseVO = userMapper.userDtoToUserVO(userResponseDto);
        return ApiResponse.success(userResponseVO);
    }

    @GetMapping("/soap/{id}")
    public String getById(@PathVariable Long id) throws Exception {
        return userSoapClient.getUserById(id);
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponseVO> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequestVO updateUserRequestVO) {
        UpdateUserRequestDto updateUserRequestDto = userMapper.updateUserVoToUserDto(updateUserRequestVO);
        UserResponseDto userResponseDto = userService.updateUser(id, updateUserRequestDto);
        UserResponseVO userResponseVO = userMapper.userDtoToUserVO(userResponseDto);
        return ApiResponse.success(userResponseVO);
    }

    @PutMapping("/soap/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UpdateUserRequestDto updateUserRequestDto) throws Exception {
        return userSoapClient.updateUserById(id, updateUserRequestDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ApiResponse.<String>builder()
                .message("User has been deleted")
                .build();
    }

    @DeleteMapping("/soap/{id}")
    public String deleteUserById(@PathVariable Long id) throws Exception {
        return userSoapClient.deleteUserById(id);
    }
}
