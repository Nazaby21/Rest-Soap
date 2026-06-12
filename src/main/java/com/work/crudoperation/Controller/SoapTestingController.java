package com.work.crudoperation.Controller;

import com.work.crudoperation.SOAP.User.UserSoapClient;
import com.work.crudoperation.generated.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class SoapTestingController {

    private final UserSoapClient userSoapClient;

    @GetMapping("/{id}")
    public GetUserResponse test(@PathVariable Long id) {

        return userSoapClient.getUser(id);
    }

}
