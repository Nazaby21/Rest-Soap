package com.work.crudoperation.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig {
//    @Bean
//    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
//        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//        servlet.setApplicationContext(applicationContext);
//        servlet.setTransformWsdlLocations(true);
//
//        return new ServletRegistrationBean<>(servlet, "/ws/*");
//    }
//
//    @Bean
//    public XsdSchema userSchema() {
//        return new SimpleXsdSchema(new ClassPathResource("xsd/user.xsd"));
//    }
//
//    @Bean(name = "users")
//    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema userSchema) {
//
//        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
//        wsdl.setPortTypeName("UserPort");
//        wsdl.setLocationUri("/ws");
//        wsdl.setTargetNamespace("http://work.com/crudoperation");
//        wsdl.setSchema(userSchema);
//        return wsdl;
//    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean<>(
                servlet,
                "/ws/*"
        );
    }

    @Bean
    public XsdSchema userSchema() {
        return new SimpleXsdSchema(
                new ClassPathResource("xsd/user.xsd")
        );
    }

    @Bean(name = "users")
    public DefaultWsdl11Definition users(XsdSchema userSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("UserPort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace("http://work.com/crudoperation");
        wsdl.setSchema(userSchema);
        return wsdl;
    }
}
