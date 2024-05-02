package com.payment.webservice;

import com.payment.webservice.configuration.SoapPhase;
import com.payment.webservice.controller.MyController;
import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.MyBankOfficialsService;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import services.payee.FindAllPayeeRequest;
import services.payee.FindAllPayeeResponse;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EndPointTesting {
    @MockBean
    private PaymentTransferRepository paymentService;

    @InjectMocks
    private SoapPhase soapPhase;


    @Mock
    MyBankOfficialsService myBankOfficialsService;

    @Test
    public void testFindAll() throws SQLSyntaxErrorException {
        Payee payee = new Payee();
        payee.setPayeeId(456);
        payee.setSenderAccountNumber(123654789987L);
        payee.setPayeeAccountNumber(123456898777L);
        payee.setPayeeName("Sanath");

        List<Payee> payeeList = new ArrayList<>();

        payeeList.add(payee);
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("user");

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
        when(myBankOfficialsService.getAccountList(123L)).thenReturn(Collections.singletonList(123654789987L));


        when(paymentService.findAllPayee(123654789987L)).thenReturn(payeeList);

        FindAllPayeeRequest request = new FindAllPayeeRequest();
        request.setSenderAccount(123654789987L);


        FindAllPayeeResponse response=soapPhase.listPayee(request);

        assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());


    }

    @Test
    public void testFindAllPayee2() throws SQLSyntaxErrorException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("payee");

        Payee payee = new Payee();
        payee.setPayeeId(456);
        payee.setSenderAccountNumber(123654789987L);
        payee.setPayeeAccountNumber(123456898777L);
        payee.setPayeeName("Sanath");

        List<Payee> payeeList = new ArrayList<>();

        payeeList.add(payee);
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("user");

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
        when(myBankOfficialsService.getAccountList(123L)).thenReturn(Collections.singletonList(1236547899L));


        when(paymentService.findAllPayee(12365478998L)).thenReturn(payeeList);

        FindAllPayeeRequest request = new FindAllPayeeRequest();
        request.setSenderAccount(123654789987L);


        FindAllPayeeResponse response=soapPhase.listPayee(request);

        assertEquals(resourceBundle.getString("no.access"), response.getServiceStatus().getMessage());

    }
}