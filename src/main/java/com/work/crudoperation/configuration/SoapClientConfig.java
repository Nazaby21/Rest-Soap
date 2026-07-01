package com.work.crudoperation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapClientConfig {
//    @Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setContextPath("com.work.crudoperation.generated");
//        return marshaller;
//    }
//
//    @Bean
//    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
//        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
//        webServiceTemplate.setMarshaller(marshaller);
//        webServiceTemplate.setUnmarshaller(marshaller);
//        return webServiceTemplate;
//    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri("http://localhost:8080/ws");
        return webServiceTemplate;
    }
}
