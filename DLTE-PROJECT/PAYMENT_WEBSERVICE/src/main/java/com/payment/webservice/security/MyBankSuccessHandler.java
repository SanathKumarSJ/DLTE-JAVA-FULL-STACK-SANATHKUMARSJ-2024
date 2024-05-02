package com.payment.webservice.security;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.service.MyBankOfficialsService;
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
import java.util.ResourceBundle;


@Component
public class MyBankSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankOfficialsService service;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("payee");
    Logger logger= LoggerFactory.getLogger(MyBankSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException, ServletException {
        Customer customer= (Customer) authentication.getPrincipal();

        if(customer.getCustomerStatus().equalsIgnoreCase("active")) {
            if (customer.getAttempts() > 1) {
                customer.setAttempts(1);
                service.updateAttempts(customer);
                logger.info(resourceBundle.getString("update.attempts"));
            }
            logger.info(resourceBundle.getString("url.redirect"));
            super.setDefaultTargetUrl("/payee/dashboard");
        }

        else{
            logger.warn(resourceBundle.getString("attempts.over"));
            super.setDefaultTargetUrl("/payee/?errors="+resourceBundle.getString("attempts.over"));
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
