package com.work.crudoperation.Mapper;

import com.work.crudoperation.DTO.Request.CreateUserRequestDto;
import com.work.crudoperation.DTO.Request.UpdateUserRequestDto;
import com.work.crudoperation.DTO.Response.UserResponseDto;
import com.work.crudoperation.Model.UserEntity;
import com.work.crudoperation.VO.RequestVO.CreateUserRequestVO;
import com.work.crudoperation.VO.RequestVO.UpdateUserRequestVO;
import com.work.crudoperation.VO.ResponseVO.UserResponseVO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // userVo to Dto
    CreateUserRequestDto createUserVoToUserDto(CreateUserRequestVO createUserRequestVO);
    UpdateUserRequestDto updateUserVoToUserDto(UpdateUserRequestVO updateUserRequestVO);

    // userDto to User
    @Mapping(target = "id", ignore = true)
    UserEntity userDtoToUserEntity(CreateUserRequestDto createUserRequestDto);

    // User to userDto
    UserResponseDto userEntityToUserDto(UserEntity userEntity);

    // userDto to Vo
    UserResponseVO userDtoToUserVO(UserResponseDto userResponseDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateUserEntityFromUserDto(UpdateUserRequestDto updateUserRequestDto, @MappingTarget UserEntity userEntity);
}
