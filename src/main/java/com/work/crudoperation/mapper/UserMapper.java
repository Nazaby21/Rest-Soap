package com.work.crudoperation.mapper;

import com.work.crudoperation.dto.Request.CreateUserRequestDto;
import com.work.crudoperation.dto.Request.UpdateUserRequestDto;
import com.work.crudoperation.dto.Response.UserResponseDto;
import com.work.crudoperation.model.UserEntity;
import com.work.crudoperation.vo.RequestVO.CreateUserRequestVO;
import com.work.crudoperation.vo.RequestVO.UpdateUserRequestVO;
import com.work.crudoperation.vo.ResponseVO.UserResponseVO;
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
