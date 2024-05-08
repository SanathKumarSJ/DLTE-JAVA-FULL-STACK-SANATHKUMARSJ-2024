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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc

public class LoginHandlerTest {
    @Mock
    private MyBankOfficialsService myBankOfficialsService;


    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private MyBankSuccessHandler successHandler;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private MyBankSuccessHandler customerSuccessHandler;


    @Mock
    private Authentication authentication;

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

    @Test
    void onAuthenticationFailureTest() throws Exception {

//        customerFailureHandler.setUseForward(true);
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setCustomerName("sanath");
        customer.setCustomerAddress("sringeri");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8277263396L);
        customer.setUserName("user");
        customer.setPassword("1234");
        customer.setAttempts(1);
        when(myBankOfficialsService.findByUsername("user")).thenReturn(customer);

        // Mocking the request parameters
        mockMvc.perform(MockMvcRequestBuilders.post("/payee/")
                .param("username", customer.getUserName())
                .param("password", customer.getPassword()))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/payee/?error=Username not found"));

    }

}