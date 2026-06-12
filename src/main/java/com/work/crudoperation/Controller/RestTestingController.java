package com.work.crudoperation.Controller;

import com.work.crudoperation.DTO.Response.UserResponseDto;
import com.work.crudoperation.REST.User.UserRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test/rest")
public class RestTestingController {
    private final UserRestClient userRestClient;

    @GetMapping("/{id}")
    public UserResponseDto test(@PathVariable Long id) {
        return userRestClient.getUser(id);
    }
}
