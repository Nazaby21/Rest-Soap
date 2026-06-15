package com.work.crudoperation.Service.FreeMarkerService;

import com.work.crudoperation.DTO.Request.CreateUserRequestDto;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FreeMarkerService {
    private final freemarker.template.Configuration configuration;

    public String generate(CreateUserRequestDto createUserRequestDto) throws Exception {
        Template template =configuration.getTemplate("user.ftl");
        Map<String,Object> data = new HashMap<>();
        data.put("username", createUserRequestDto.getUsername());
        data.put("email", createUserRequestDto.getEmail());
        data.put("password", createUserRequestDto.getPassword());

        StringWriter stringWriter = new StringWriter();
        template.process(data, stringWriter);
        return stringWriter.toString();
    }

    public String generateGetAllUsers() throws Exception {
        Template template = configuration.getTemplate("getAllUser.ftl");
        StringWriter writer = new StringWriter();
        template.process(new HashMap<>(), writer);
        return writer.toString();
    }
}
