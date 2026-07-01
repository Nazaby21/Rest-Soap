package com.work.crudoperation.service.serviceImpl;

import com.work.crudoperation.model.ErrorMessage;
import com.work.crudoperation.repository.ErrorMessageRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErrorMessageService {
    private final ErrorMessageRepository repository;
    @Cacheable(value = "message", key = "#code")
    public ErrorMessage getErrorMessage(String code){
        System.out.println("Query database...");
        return repository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Error message not found"));
    }
}
