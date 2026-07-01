package com.work.crudoperation.service.freemarker_service;

import com.work.crudoperation.dto.Request.CreateUserRequestDto;
import com.work.crudoperation.dto.Request.UpdateUserRequestDto;
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
        Template template = configuration.getTemplate("user.ftl");
        Map<String, Object> data = new HashMap<>();
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

    public String generateUserById(Long id) throws Exception {
        Template template = configuration.getTemplate("getById.ftl");
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        return writer.toString();
    }

    public String generateUpdateUser(Long id, UpdateUserRequestDto updateUserRequestDto) throws Exception {
        Template template = configuration.getTemplate("updateUser.ftl");
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("username", updateUserRequestDto.getUsername());
        data.put("email", updateUserRequestDto.getEmail());
        data.put("password", updateUserRequestDto.getPassword());
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        return writer.toString();
    }

    public String generateDeleteById(Long id) throws Exception {
        Template template = configuration.getTemplate("deleteById.ftl");
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        return writer.toString();
    }
}
