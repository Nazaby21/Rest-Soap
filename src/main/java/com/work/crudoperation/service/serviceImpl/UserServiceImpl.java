package com.work.crudoperation.service.serviceImpl;

import com.work.crudoperation.dto.Request.CreateUserRequestDto;
import com.work.crudoperation.dto.Request.UpdateUserRequestDto;
import com.work.crudoperation.dto.Response.UserResponseDto;
import com.work.crudoperation.exception.custom.BusinessException;
import com.work.crudoperation.exception.custom.ResourceAlreadyExistException;
import com.work.crudoperation.exception.custom.ResourceNotFoundException;
import com.work.crudoperation.mapper.UserMapper;
import com.work.crudoperation.model.UserEntity;
import com.work.crudoperation.repository.UserRepository;
import com.work.crudoperation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ErrorMessageService errorMessageService;

    @Override
    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        if (userRepository.existsByEmail(createUserRequestDto.getEmail())) {
            throw ResourceAlreadyExistException.byField("User", "email", createUserRequestDto.getEmail());
        }
        UserEntity userEntity = userMapper.userDtoToUserEntity(createUserRequestDto);
        UserEntity saveUser = userRepository.save(userEntity);
        return userMapper.userEntityToUserDto(saveUser);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow();
        return userMapper.userEntityToUserDto(user);
    }

    @Override
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userEntityToUserDto)
                .toList();
    }

    @Override
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto updateUserRequestDto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.byId("User", id));
        if (userRepository.existsByEmail(updateUserRequestDto.getEmail())) {
            throw ResourceAlreadyExistException.byField("User", "email", updateUserRequestDto.getEmail());
        }
        userMapper.updateUserEntityFromUserDto(updateUserRequestDto, user);
        UserEntity saveUser = userRepository.save(user);
        return userMapper.userEntityToUserDto(saveUser);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.byId("User", id));
        userRepository.delete(user);
    }
}
