package com.payment.webservice;


import com.payment.webservice.security.MyBankFailureHandler;
import com.payment.webservice.security.MyBankSuccessHandler;
import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.service.MyBankOfficialsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoginHandlerTest {
    @Mock
    private MyBankOfficialsService myBankOfficialsService;

    @InjectMocks
    private MyBankSuccessHandler successHandler;

    @InjectMocks
    private MyBankFailureHandler failureHandler;

    @Test
    public void testMaxAttemptsReached() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Authentication authentication = mock(Authentication.class);
        Customer myBankOfficials = new Customer();
        myBankOfficials.setCustomerStatus("inactive"); // Assuming status indicates maximum attempts reached
        when(authentication.getPrincipal()).thenReturn(myBankOfficials);

        successHandler.onAuthenticationSuccess(request, response, authentication);

        assertEquals("/payee/?errors=Max attempts reached contact admin", response.getRedirectedUrl());
    }

    @Test
    public void testSuccessHandler() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Authentication authentication = mock(Authentication.class);
        String username = "testUser";

        Customer myBankOfficials = new Customer();
        myBankOfficials.setUserName(username);
        myBankOfficials.setCustomerStatus("active");
        myBankOfficials.setAttempts(2);
        when(authentication.getPrincipal()).thenReturn(myBankOfficials);

        successHandler.onAuthenticationSuccess(request, response, authentication);

        assertEquals("/payee/dashboard", response.getRedirectedUrl());

    }

}