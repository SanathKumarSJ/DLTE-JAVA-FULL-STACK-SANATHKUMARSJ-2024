package com.payment.webservice;

import com.payment.webservice.controller.MyController;
import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.exception.PayeeExistException;
import com.paymentdao.payment.exception.PayeeNotExistException;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RestEndPointTest {

    @MockBean
    private PaymentTransferRepository paymentTransferRepository;

    @InjectMocks
    private MyController myController;

    @Mock
    MyBankOfficialsService myBankOfficialsService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
    }

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

        when(paymentTransferRepository.addNewPayee(payee)).thenReturn(resourceBundle.getString("payee.ok"));

        ResponseEntity<String> responseEntity = myController.newPayee(payee);

        assertEquals(resourceBundle.getString("payee.ok"), responseEntity.getBody());

    }

    @Test
    void testPayeeExistException() throws Exception {
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

        String requestBody = "{\"payeeId\":456,\"senderAccountNumber\":123654789987,\"payeeAccountNumber\":123456898777,\"payeeName\":\"Sanath\"}";

        when(myBankOfficialsService.findByUsername("user")).thenReturn(customer);
        when(myBankOfficialsService.getAccountList(123L)).thenReturn(Collections.singletonList(123654789987L));
        when(paymentTransferRepository.addNewPayee(any(Payee.class))).thenThrow(new PayeeExistException("Payee record already exists"));

        mockMvc.perform(post("/pay/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("EXC002 :Payee record already exists"));

    }

    @Test
    void testNewPayeeException() throws Exception {

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

        String requestBody = "{\"payeeId\":456,\"senderAccountNumber\":123654789987,\"payeeAccountNumber\":123456898777,\"payeeName\":\"Sanath\"}";

        when(myBankOfficialsService.findByUsername("user")).thenReturn(customer);
        when(myBankOfficialsService.getAccountList(123L)).thenReturn(Collections.singletonList(123654789987L));
        when(paymentTransferRepository.addNewPayee(any(Payee.class))).thenThrow(new PayeeNotExistException("Payee record not exist"));

        mockMvc.perform(post("/pay/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("EXC003 :Payee record not exist"));

    }

    @Test
    void testNewPayeeExist() throws Exception {
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

        String requestBody = "{\"payeeId\":456,\"senderAccountNumber\":123654789987,\"payeeAccountNumber\":123456898777,\"payeeName\":\"Sanath\"}";

        when(myBankOfficialsService.findByUsername("user")).thenReturn(customer);
        when(myBankOfficialsService.getAccountList(123L)).thenReturn(Collections.singletonList(123654789987L));
        when(paymentTransferRepository.addNewPayee(any(Payee.class))).thenThrow(new PayeeException("Payee record already exists"));


        mockMvc.perform(post("/pay/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("EXC001 :Payee record already exists"));

    }

    @Test
    void testGetAccountNumber() throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("payee");

        Payee payee = new Payee();
        payee.setPayeeId(456);
        payee.setSenderAccountNumber(123654789987L);
        payee.setPayeeAccountNumber(123456898777L);
        payee.setPayeeName("Sanath");

        Payee payee2 = new Payee();
        payee2.setPayeeId(457);
        payee2.setSenderAccountNumber(963258741236L);
        payee2.setPayeeAccountNumber(789654123321L);
        payee2.setPayeeName("Akash");

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


        String requestBody = "[{\"payeeId\":456,\"senderAccountNumber\":123654789987,\"payeeAccountNumber\":123456898777,\"payeeName\":\"Sanath\"},{\"payeeId\":457,\"senderAccountNumber\": 963258741236,\"payeeAccountNumber\":789654123321,\"payeeName\":\"Akash\"}}";

        List<Long> accountList = Stream.of(123654789987L, 963258741236L).collect(Collectors.toList());
        when(myBankOfficialsService.findByUsername("user")).thenReturn(customer);
        when(myBankOfficialsService.getAccountList(123L)).thenReturn(accountList);


        mockMvc.perform(get("/pay/getAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(accountList.toString()));

    }

    @Test
    @WithMockUser(username = "user")
    void testAddPayeeUrl() throws Exception {

        Payee payee = new Payee();
        payee.setPayeeId(456);
        payee.setSenderAccountNumber(123654789987L);
        payee.setPayeeAccountNumber(123456898777L);
        payee.setPayeeName("Sanath");

        String requestBody = "{\"payeeId\":456,\"senderAccountNumber\":123654789987,\"payeeAccountNumber\":123456898777,\"payeeName\":\"Sanath\"}";

        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setCustomerName("sanath");
        customer.setCustomerAddress("sringeri");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8277263396L);
        customer.setUserName("user");
        customer.setPassword("1234");
        customer.setAttempts(1);

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("user");

        when(myBankOfficialsService.findByUsername("user")).thenReturn(customer);
        when(myBankOfficialsService.getAccountList(123L)).thenReturn(Collections.singletonList(123654789987L));

        when((paymentTransferRepository).addNewPayee(payee)).thenReturn("Inserted");

        mockMvc.perform(post("/pay/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Payee data inserted successfully"));

    }

    @Test
    @WithMockUser(username = "user")
    void testAddPayeeUrlInvalid() throws Exception {

        Payee payee = new Payee();
        payee.setPayeeId(456);
        payee.setSenderAccountNumber(123654789987L);
        payee.setPayeeAccountNumber(123456898777L);
        payee.setPayeeName("Sanath");

        String requestBody = "{\"payeeId\":456,\"senderAccountNumber\":123654789987,\"payeeAccountNumber\":123456898777,\"payeeName\":\"Sanath\"}";

        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setCustomerName("sanath");
        customer.setCustomerAddress("sringeri");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8277263396L);
        customer.setUserName("user");
        customer.setPassword("1234");
        customer.setAttempts(1);

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        lenient().when(authentication.getName()).thenReturn("user");

        lenient().when(myBankOfficialsService.findByUsername("user")).thenReturn(customer);
        lenient().when(myBankOfficialsService.getAccountList(123L)).thenReturn(Collections.singletonList(123654789987L));

        lenient().when((paymentTransferRepository).addNewPayee(payee)).thenReturn("Inserted");

        mockMvc.perform(post("/pay/ad")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));

    }

}