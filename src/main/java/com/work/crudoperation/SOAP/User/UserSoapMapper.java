package com.work.crudoperation.SOAP.User;

import com.work.crudoperation.DTO.Request.CreateUserRequestDto;
import com.work.crudoperation.DTO.Request.UpdateUserRequestDto;
import com.work.crudoperation.DTO.Response.UserResponseDto;
import com.work.crudoperation.generated.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserSoapMapper {

    // map createUserRequest(soap) to createUserRequest(rest)
    CreateUserRequestDto createSoapToCreateRest(CreateUserRequest createUserRequest);
    UpdateUserRequestDto updateSoapToUpdateRest(UpdateUserRequest updateUserRequest);


    // map userResponse(rest) to createUserResponse(soap)
    CreateUserResponse responseRestToResponseSoap(UserResponseDto userResponseDto);
    UpdateUserResponse updateResponseRestToResponseSoap(UserResponseDto userResponseDto);
    GetUserResponse userResponseRestToUserResponseSoap(UserResponseDto userResponseDto);

    // responseSoap to ResponseRest
    UserResponseDto soapToDto(GetUserResponse UserResponse);

}
