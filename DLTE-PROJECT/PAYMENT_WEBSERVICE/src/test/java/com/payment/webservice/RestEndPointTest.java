package com.payment.webservice;

import com.payment.webservice.controller.MyController;
import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.MyBankOfficialsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RestEndPointTest {

    @MockBean
    private PaymentTransferRepository paymentTransferRepository;

    @InjectMocks
    private MyController myController;


    @Mock
    MyBankOfficialsService myBankOfficialsService;

    @Test
    public void addPayeeTest() {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("payee");

            Payee payee = new Payee();
            payee.setPayeeId(456);
            payee.setSenderAccountNumber(123654789987L);
            payee.setPayeeAccountNumber(123456898777L);
            payee.setPayeeName("Sanath");

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

            when(paymentTransferRepository.addNewPayee(payee)).thenReturn(resourceBundle.getString("payee.ok"));

            ResponseEntity<String> responseEntity = myController.newPayee(payee);

            assertNotEquals(resourceBundle.getString("no.access"), responseEntity.getBody());

        }


    @Test
    public void addPayeeTest2() {
        Payee payee = new Payee();
        payee.setPayeeId(456);
        payee.setSenderAccountNumber(123654789987L);
        payee.setPayeeAccountNumber(123456898777L);
        payee.setPayeeName("Sanath");

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("user");


        // Mock service behavior
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

        when(paymentTransferRepository.addNewPayee(payee)).thenReturn("Payee added successfully");

        ResponseEntity<String> responseEntity = myController.newPayee(payee);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }


    @Test
    public void testAddPayee3() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("payee");

        Payee payee = new Payee();
        payee.setPayeeId(456);
        payee.setSenderAccountNumber(123654789987L);
        payee.setPayeeAccountNumber(123456898777L);
        payee.setPayeeName("Sanath");

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("user");


        // Mock service behavior
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

        when(paymentTransferRepository.addNewPayee(payee)).thenReturn(resourceBundle.getString("payee.ok"));

        ResponseEntity<String> responseEntity = myController.newPayee(payee);

        assertEquals(resourceBundle.getString("payee.ok"), responseEntity.getBody());

    }



}