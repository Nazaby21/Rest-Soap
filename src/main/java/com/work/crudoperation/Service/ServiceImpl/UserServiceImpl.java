package com.work.crudoperation.Service.ServiceImpl;

import com.work.crudoperation.DTO.Request.CreateUserRequestDto;
import com.work.crudoperation.DTO.Request.UpdateUserRequestDto;
import com.work.crudoperation.DTO.Response.UserResponseDto;
import com.work.crudoperation.Exception.Custom.ResourceAlreadyExistException;
import com.work.crudoperation.Exception.Custom.ResourceNotFoundException;
import com.work.crudoperation.Mapper.UserMapper;
import com.work.crudoperation.Model.UserEntity;
import com.work.crudoperation.Repository.UserRepository;
import com.work.crudoperation.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
                .orElseThrow(() -> ResourceNotFoundException.byId("User", id)
                );

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
