package com.work.crudoperation.Controller;

import com.work.crudoperation.DTO.Request.CreateUserRequestDto;
import com.work.crudoperation.DTO.Request.UpdateUserRequestDto;
import com.work.crudoperation.DTO.Response.UserResponseDto;
import com.work.crudoperation.Exception.DTO.ApiResponse;
import com.work.crudoperation.Mapper.UserMapper;
import com.work.crudoperation.Service.UserService;
import com.work.crudoperation.VO.RequestVO.CreateUserRequestVO;
import com.work.crudoperation.VO.RequestVO.UpdateUserRequestVO;
import com.work.crudoperation.VO.ResponseVO.UserResponseVO;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ApiResponse<UserResponseVO> createUser(@RequestBody CreateUserRequestVO createUserRequestVO) {
        CreateUserRequestDto createUserRequestDto = userMapper.createUserVoToUserDto(createUserRequestVO);
        UserResponseDto userResponseDto = userService.createUser(createUserRequestDto);
        UserResponseVO userResponseVO = userMapper.userDtoToUserVO(userResponseDto);
        return ApiResponse.success(userResponseVO);
    }

    @GetMapping()
    public ApiResponse<List<UserResponseVO>> getUsers() {
        List<UserResponseVO> users = userService.getUsers()
                .stream()
                .map(userMapper::userDtoToUserVO)
                .toList();
        return ApiResponse.success(users);
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponseVO> getUserById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        UserResponseVO userResponseVO = userMapper.userDtoToUserVO(userResponseDto);
        return ApiResponse.success(userResponseVO);
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponseVO> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequestVO updateUserRequestVO) {
        UpdateUserRequestDto updateUserRequestDto = userMapper.updateUserVoToUserDto(updateUserRequestVO);
        UserResponseDto userResponseDto = userService.updateUser(id, updateUserRequestDto);
        UserResponseVO userResponseVO = userMapper.userDtoToUserVO(userResponseDto);
        return ApiResponse.success(userResponseVO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ApiResponse.<String>builder()
                .message("User has been deleted")
                .build();
    }
}
