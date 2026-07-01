package com.work.crudoperation.rest.user;

import com.work.crudoperation.dto.Response.ApiResponse;
import com.work.crudoperation.dto.Response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class UserRestClient {
    private final RestClient restClient;

    public UserResponseDto getUser(Long id) {
        ApiResponse<UserResponseDto> response =
                restClient.get()
                        .uri("/api/user/{id}", id)
                        .retrieve()
                        .body(
                                new ParameterizedTypeReference<ApiResponse<UserResponseDto>>() {
                                }
                        );
        return response.getData();
    }
}
