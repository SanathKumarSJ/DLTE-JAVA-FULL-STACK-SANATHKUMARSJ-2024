package com.payment.webservice;

import com.payment.webservice.configuration.SoapPhase;
import com.payment.webservice.controller.MyController;
import com.payment.webservice.mvc.PaymentModelViewController;
import com.payment.webservice.security.MyBankOfficialsAPI;
import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.MyBankOfficialsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import services.payee.FindAllPayeeRequest;
import services.payee.FindAllPayeeResponse;

import java.sql.SQLSyntaxErrorException;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EndPointTesting {
    @MockBean
    private PaymentTransferRepository paymentTransferRepository;

    @InjectMocks
    private MyController myController;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    MyBankOfficialsService myBankOfficialsService;

    @InjectMocks
    private SoapPhase soapPhase;

    private MockMvc mockMvc;
    private MockMvc mockMvc2;

    @InjectMocks
    private PaymentModelViewController paymentModelController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
        mockMvc2 = MockMvcBuilders.standaloneSetup(paymentModelController).build();
    }


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


        when(paymentTransferRepository.findAllPayee(123654789987L)).thenReturn(payeeList);

        FindAllPayeeRequest request = new FindAllPayeeRequest();
        request.setSenderAccount(123654789987L);


        FindAllPayeeResponse response = soapPhase.listPayee(request);

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


        when(paymentTransferRepository.findAllPayee(12365478998L)).thenReturn(payeeList);

        FindAllPayeeRequest request = new FindAllPayeeRequest();
        request.setSenderAccount(123654789987L);


        FindAllPayeeResponse response = soapPhase.listPayee(request);

        assertEquals(resourceBundle.getString("no.access"), response.getServiceStatus().getMessage());

    }

    @Test
    void testListPayeeException() throws SQLSyntaxErrorException {
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
        when(myBankOfficialsService.getAccountList(123L)).thenReturn(Collections.singletonList(123654789987L));

        FindAllPayeeRequest request = new FindAllPayeeRequest();
        request.setSenderAccount(123654789987L);
        when(paymentTransferRepository.findAllPayee(123654789987L))
                .thenThrow(new PayeeException("Payee not found"));


        FindAllPayeeResponse response = soapPhase.listPayee(request);

        assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
        assertEquals("EXC005 :Payee not found", response.getServiceStatus().getMessage());

    }

    @Test
    void testIndexPage() throws Exception {
        mockMvc2.perform(get("/payee/"))
                .andExpect(view().name("index"));
    }

    @Test
    void testLoginError() throws Exception {
        mockMvc2.perform(post("/payee/"))
                .andExpect(view().name("index"));
    }

    @Test
    void testHomePage() throws Exception {
        mockMvc2.perform(get("/payee/dashboard"))
                .andExpect(view().name("dash"));
    }

    @Test
    void testViewPage() throws Exception {
        mockMvc2.perform(get("/payee/view"))
                .andExpect(view().name("viewPayee"));
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testErrorView() {
        String viewName = paymentModelController.errorPage();
        assertEquals("error", viewName);
    }


    @Test
    void testShow() throws Exception {
        mockMvc2.perform(get("/payee/add"))
                .andExpect(view().name("addpayee"));
    }

    @Test
    public void testError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/payee/error")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testCustomerName() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setCustomerName("sanath");
        customer.setCustomerAddress("sringeri");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8277263396L);
        customer.setUserName("user");
        customer.setPassword("1234");
        customer.setAttempts(1);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("username");

        when(myBankOfficialsService.findByUsername("username")).thenReturn(customer);

        mockMvc2.perform(get("/payee/username"))
                .andExpect(status().isOk())
                .andExpect(content().string("sanath"));
    }


    @Test
    public void testHandleValidationExceptions() {

        BindingResult bindingResult = mock(BindingResult.class);

        FieldError fieldError1 = new FieldError("payee", "payeeAccountNumber", "Account number is required");
        FieldError fieldError2 = new FieldError("payee", "payeeName", "Name is is required");

        Mockito.when(bindingResult.getAllErrors()).thenReturn(Arrays.asList(fieldError1, fieldError2));

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        Map<String, String> result = myController.handleValidationExceptions(exception);
        assertEquals(result.get("payeeAccountNumber"), "Account number is required");
        assertEquals(result.get("payeeName"), "Name is is required");
    }

    //for password check
    @Test
    public void testPasswordMatch() {
        MyBankOfficialsService myBankUsersServices = mock(MyBankOfficialsService.class);
        passwordEncoder = new BCryptPasswordEncoder();
        String username = "sanath";
        String rawPassword = "san123";

        String encodedPassword = passwordEncoder.encode(rawPassword);

        Customer customer = new Customer();
        customer.setUserName(username);
        customer.setPassword(encodedPassword);
        when(myBankUsersServices.loadUserByUsername(username))
                .thenReturn(customer);

        UserDetails userDetails = myBankUsersServices.loadUserByUsername(username);

        String enteredPassword = "san123";

        assertTrue(passwordEncoder.matches(enteredPassword, userDetails.getPassword()));
    }
}