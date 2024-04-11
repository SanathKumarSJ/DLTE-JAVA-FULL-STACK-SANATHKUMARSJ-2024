package com.employee.web.webservice.controller;

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


@EnableWs
@Configuration
public class SoapConfig {
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
    MessageDispatcherServlet servlet=new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet,"/emprepo/*");
}

    // wsdl properties
    @Bean(name = "Employee")
    public DefaultWsdl11Definition convertToWsdl(XsdSchema xsdSchema){
        DefaultWsdl11Definition defaultWsdl11Definition=new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("EmployeePort");
        defaultWsdl11Definition.setTargetNamespace("http://employee.services");
        defaultWsdl11Definition.setLocationUri("/emprepo");
        defaultWsdl11Definition.setSchema(xsdSchema);
        return defaultWsdl11Definition;
    }

    // identify the xsd
    @Bean
    public XsdSchema loansSchema(){
        return new SimpleXsdSchema(new ClassPathResource("employee.xsd"));
    }
}