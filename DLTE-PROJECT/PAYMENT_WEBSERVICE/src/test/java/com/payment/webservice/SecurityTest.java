package com.payment.webservice;


import com.payment.webservice.configuration.SoapPhase;
import com.payment.webservice.security.MyBankFailureHandler;
import com.payment.webservice.security.MyBankSuccessHandler;
import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.MyBankOfficialsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import services.payee.FindAllPayeeRequest;
import services.payee.FindAllPayeeResponse;
import services.payee.ServiceStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLSyntaxErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SecurityTest {
    @Mock
    private MyBankOfficialsService myBankOfficialsService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private AuthenticationException exception;

    @InjectMocks
    private MyBankFailureHandler failureHandler;


    @Mock
    private Authentication authentication;



    @InjectMocks
    private MyBankSuccessHandler customerSuccessHandler;


    @Test
    public void testOnAuthenticationSuccess() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerStatus("inactive");
        when(authentication.getPrincipal()).thenReturn(customer);

        customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).encodeRedirectURL("null/payee/?errors=Max attempts reached contact admin");
    }

    @Test
    public void testOnAuthenticationSuccess2() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setCustomerName("sanath");
        customer.setCustomerAddress("sringeri");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8277263396L);
        customer.setUserName("user");
        customer.setPassword("1234");
        customer.setAttempts(1);
        when(authentication.getPrincipal()).thenReturn(customer);

        customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).encodeRedirectURL("null/payee/dashboard");
    }

    @Mock
    private SpringApplicationBuilder mockApplicationBuilder;

    @Test
    void configureTest() {
        ServletInitializer servletInitializer = new ServletInitializer();

        servletInitializer.configure(mockApplicationBuilder);

        verify(mockApplicationBuilder).sources(WebserviceApplication.class);
    }
}
