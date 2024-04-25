package com.payment.webservice.security;

import com.paymentdao.payment.entity.Customer;
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
import java.util.ResourceBundle;

@Component
public class MyBankFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    MyBankOfficialsService service;

    Logger logger= LoggerFactory.getLogger(MyBankFailureHandler.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("payee");

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        Customer customer = service.findByUsername(username);

        if(customer!=null){
            if(customer.getCustomerStatus().equalsIgnoreCase("active")){
                if(customer.getAttempts()< customer.getMaxAttempt()){
                    customer.setAttempts(customer.getAttempts()+1);
                    service.updateAttempts(customer);
                    logger.warn(resourceBundle.getString("user.invalid"));
                    exception=new LockedException(resourceBundle.getString("get.attempt"));
                }
                else{
                    service.updateStatus(customer);
                    logger.warn(resourceBundle.getString("user.suspend"));
                    exception=new LockedException(resourceBundle.getString("user.suspend"));
                }
            }
            else{
                logger.warn(resourceBundle.getString("user.inactive"));
            }
        }

        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }
}