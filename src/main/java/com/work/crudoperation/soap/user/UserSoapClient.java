package com.work.crudoperation.soap.user;


import com.work.crudoperation.dto.Request.CreateUserRequestDto;
import com.work.crudoperation.dto.Request.UpdateUserRequestDto;
import com.work.crudoperation.service.freemarker_service.FreeMarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

@Service
@RequiredArgsConstructor
public class UserSoapClient {
    private final WebServiceTemplate webServiceTemplate;
    private final FreeMarkerService freeMarkerService;

    //    public GetUserResponse getUser(Long id) {
//        GetUserRequest request = new GetUserRequest();
//
//        request.setId(id);
//        return (GetUserResponse) webServiceTemplate.marshalSendAndReceive("http://localhost:8080/ws",request);
//    }
    public String createUser(CreateUserRequestDto request) throws Exception {
        String soapXml = freeMarkerService.generate(request);
        StringResult result = new StringResult();
        webServiceTemplate.sendSourceAndReceiveToResult(new StringSource(soapXml), result);
        return result.toString();
    }

    public String getAllUsers() throws Exception {
        String xml = freeMarkerService.generateGetAllUsers();
        StringResult result = new StringResult();
        webServiceTemplate.sendSourceAndReceiveToResult(new StringSource(xml), result);
        return result.toString();
    }

    public String getUserById(Long id) throws Exception {
        String xml = freeMarkerService.generateUserById(id);
        StringResult result = new StringResult();
        webServiceTemplate.sendSourceAndReceiveToResult(new StringSource(xml), result);
        return result.toString();
    }

    public String updateUserById(Long id, UpdateUserRequestDto request) throws Exception {
        String xml = freeMarkerService.generateUpdateUser(id, request);
        StringResult result = new StringResult();
        webServiceTemplate.sendSourceAndReceiveToResult(new StringSource(xml), result);
        return result.toString();
    }

    public String deleteUserById(Long id) throws Exception {
        String xml = freeMarkerService.generateDeleteById(id);
        StringResult result = new StringResult();
        webServiceTemplate.sendSourceAndReceiveToResult(new StringSource(xml), result);
        return result.toString();
    }

}
