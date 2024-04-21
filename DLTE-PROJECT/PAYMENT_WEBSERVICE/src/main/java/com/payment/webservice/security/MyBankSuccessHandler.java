package com.payment.webservice.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyBankSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankOfficialsService service;

    Logger logger= LoggerFactory.getLogger(MyBankSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException, ServletException {
        MyBankOfficials myBankOfficials= (MyBankOfficials) authentication.getPrincipal();
        if(myBankOfficials.getStatus()!=0){
            if(myBankOfficials.getAttempts()>1){
                myBankOfficials.setAttempts(1);
                service.updateAttempts(myBankOfficials);
            }
            super.setDefaultTargetUrl("http://localhost:8082/payeerepo/Payee.wsdl");
        }
        else{
            logger.warn("Max attempts reached contact admin");
            super.setDefaultTargetUrl("/login");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}