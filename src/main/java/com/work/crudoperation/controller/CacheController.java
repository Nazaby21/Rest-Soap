//package com.work.crudoperation.controller;
//
//import com.work.crudoperation.utils.cache.ErrorMessageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/cache")
//public class CacheController {
//    private final ErrorMessageService errorMessageService;
//
//    @GetMapping
//    public Map<String, String> getCache() {
//
//        return errorMessageService.getCache();
//
//    }
//
//}