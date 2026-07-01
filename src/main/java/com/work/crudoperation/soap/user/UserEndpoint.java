package com.work.crudoperation.soap.user;

import com.work.crudoperation.dto.Request.CreateUserRequestDto;
import com.work.crudoperation.dto.Request.UpdateUserRequestDto;
import com.work.crudoperation.dto.Response.UserResponseDto;
import com.work.crudoperation.generated.*;
import com.work.crudoperation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

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
    public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {

        CreateUserRequestDto createUserRequestDto = userSoapMapper.createSoapToCreateRest(request);
        UserResponseDto user = userService.createUser(createUserRequestDto);
        return userSoapMapper.responseRestToResponseSoap(user);

//        CreateUserRequestDto dto = new CreateUserRequestDto();
//        dto.setUsername(request.getUsername());
//        dto.setEmail(request.getEmail());
//        dto.setPassword(request.getPassword());
//        UserResponseDto result = userService.createUser(dto);
//        CreateUserResponse response = new CreateUserResponse();
//        response.setId(result.getId());
//        response.setUsername(result.getUsername());
//        response.setEmail(result.getEmail());
//        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest updateUserRequest) {
        UpdateUserRequestDto updateUserRequestDto = userSoapMapper.updateSoapToUpdateRest(updateUserRequest);
        UserResponseDto updateUserResponse = userService.updateUser(updateUserRequest.getId(), updateUserRequestDto);

        return userSoapMapper.updateResponseRestToResponseSoap(updateUserResponse);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest getUserRequest) {
        UserResponseDto userResponseDto =
                userService.getUserById(getUserRequest.getId());

        return userSoapMapper.userResponseRestToUserResponseSoap(userResponseDto);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest getAllUsersRequest) {
        List<UserResponseDto> userResponseDtos = userService.getUsers();

        GetAllUsersResponse getAllUsersResponse = new GetAllUsersResponse();

        for (UserResponseDto userResponseDto : userResponseDtos) {
            GetAllUsersResponse.Users soapUser = new GetAllUsersResponse.Users();
            soapUser.setId(userResponseDto.getId());
            soapUser.setUsername(userResponseDto.getUsername());
            soapUser.setEmail(userResponseDto.getEmail());
            getAllUsersResponse.getUsers().add(soapUser);
        }

        return getAllUsersResponse;
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
