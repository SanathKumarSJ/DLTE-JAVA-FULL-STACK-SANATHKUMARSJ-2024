package com.payment.webservice.security;


import com.paymentdao.payment.entity.MyBankOfficials;
import com.paymentdao.payment.service.MyBankOfficialsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyBankFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    MyBankOfficialsService service;

    Logger logger= LoggerFactory.getLogger(MyBankFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        MyBankOfficials myBankOfficials = service.findByUsername(username);
        if(myBankOfficials!=null){
            logger.info(myBankOfficials.getCustomerName()+myBankOfficials.getUserName()+myBankOfficials.getCustomerContact());
            if(myBankOfficials.getCustomerStatus().equalsIgnoreCase("active")){
                if(myBankOfficials.getAttempts()< myBankOfficials.getMaxAttempt()){
                    myBankOfficials.setAttempts(myBankOfficials.getAttempts()+1);
                    service.updateAttempts(myBankOfficials);
                    logger.warn("Invalid credentials and attempts taken");
                    exception=new LockedException("Attempts are taken");
                }
                else{
                    service.updateStatus(myBankOfficials);
                    logger.warn("Max Attempts reached account is suspended");
                    exception=new LockedException("Max Attempts reached account is suspended");
                }
            }
            else{
                logger.warn("Account suspended contact admin to redeem");
            }
        }
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }
}