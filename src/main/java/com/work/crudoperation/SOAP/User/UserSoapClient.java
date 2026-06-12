package com.work.crudoperation.SOAP.User;


import com.work.crudoperation.generated.GetUserRequest;
import com.work.crudoperation.generated.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
@RequiredArgsConstructor
public class UserSoapClient {
    private final WebServiceTemplate webServiceTemplate;
    public GetUserResponse getUser(Long id) {
        GetUserRequest request = new GetUserRequest();

        request.setId(id);
        return (GetUserResponse) webServiceTemplate.marshalSendAndReceive("http://localhost:8080/ws",request);
    }
}
