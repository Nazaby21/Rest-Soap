package com.work.crudoperation.utils.cache;

import com.work.crudoperation.model.ErrorMessage;
import com.work.crudoperation.repository.ErrorMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErrorMessageServices {
    private final ErrorMessageRepository errorMessageRepository;
    private final LocalCache localCache;

    public String getErrorMessage(String code){
        String message = localCache.get(code);
        if(message != null){
            return message;
        }

        ErrorMessage errorMessage = errorMessageRepository
                .findByCode(code)
                .orElseThrow();

        message = errorMessage.getMessage();

        localCache.put(code, message, 600000L);
        return message;
    }
}
