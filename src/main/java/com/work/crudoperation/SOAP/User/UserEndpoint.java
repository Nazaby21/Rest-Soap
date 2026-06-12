package com.work.crudoperation.SOAP.User;

import com.work.crudoperation.DTO.Request.CreateUserRequestDto;
import com.work.crudoperation.DTO.Request.UpdateUserRequestDto;
import com.work.crudoperation.DTO.Response.UserResponseDto;
import com.work.crudoperation.Service.UserService;
import com.work.crudoperation.generated.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://work.com/crudoperation";
    private final UserService userService;
    private final UserSoapMapper userSoapMapper;

    /*@PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateUserRequest")
    @ResponsePayload
    public CreateUserRequest  createUser(@RequestPayload CreateUserRequest createUserRequest) {
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();

        createUserRequestDto.setUsername(createUserRequest.getUsername());
        createUserRequestDto.setEmail(createUserRequest.getEmail());
        createUserRequestDto.setPassword(createUserRequest.getPassword());

        UserResponseDto userResponseDto = userService.createUser(createUserRequestDto);

        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setId(userResponseDto.getId());
        createUserResponse.setUsername(userResponseDto.getUsername());
        createUserResponse.setEmail(userResponseDto.getEmail());

        return userResponseDto;
    }*/

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateUserRequest")
    @ResponsePayload
    public CreateUserResponse createUser(@RequestPayload CreateUserRequest createUserRequest) {
        CreateUserRequestDto createUserRequestDto = userSoapMapper.createSoapToCreateRest(createUserRequest);
        UserResponseDto userResponseDto = userService.createUser(createUserRequestDto);

        return userSoapMapper.responseRestToResponseSoap(userResponseDto);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest updateUserRequest) {
        UpdateUserRequestDto updateUserRequestDto = userSoapMapper.updateSoapToUpdateRest(updateUserRequest);
        UserResponseDto updateUserResponse = userService.updateUser(updateUserRequest.getId(),updateUserRequestDto);

        return userSoapMapper.updateResponseRestToResponseSoap(updateUserResponse);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest getUserRequest) {
        UserResponseDto userResponseDto =
                userService.getUserById(getUserRequest.getId());

        return userSoapMapper.userResponseRestToUserResponseSoap(userResponseDto);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest deleteUserRequest) {
        userService.deleteUser(deleteUserRequest.getId());

        DeleteUserResponse deleteUserResponse = new DeleteUserResponse();

        deleteUserResponse.setMessage("User has been deleted");
        return deleteUserResponse;
    }


}
