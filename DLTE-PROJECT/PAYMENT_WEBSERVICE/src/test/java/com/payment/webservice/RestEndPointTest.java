//package com.payment.webservice;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.payment.webservice.controller.MyController;
//import com.paymentdao.payment.entity.Payee;
//import com.paymentdao.payment.exception.CollusionException;
//import com.paymentdao.payment.exception.PayeeException;
//import com.paymentdao.payment.remote.PaymentTransferRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@ExtendWith(MockitoExtension.class)
//class RestEndPointTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @InjectMocks
//    private MyController myController;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Mock
//    private PaymentTransferRepository paymentTransferRepository;
//
//    @Test
//    void testNewPayeeSuccess() {
//        Payee payee = new Payee(); // Payee object
//        payee.setPayeeId(123);
//        payee.setSenderAccountNumber(123456789100L);
//        payee.setPayeeAccountNumber(987654321300L);
//        payee.setPayeeName("Arundhathi");
//
//        when(paymentTransferRepository.addNewPayee(payee)).thenReturn("Payee added successfully");
//
//        ResponseEntity<String> responseEntity = myController.newPayee(payee);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        // Add more assertions if needed
//    }
//
//    @Test
//    void testNewPayeeException() {
//        Payee payee = new Payee(); // create a valid Payee object
//        payee.setPayeeId(123);
//        payee.setSenderAccountNumber(123456789100L);
//        payee.setPayeeAccountNumber(123456789101L);
//        payee.setPayeeName("sanath");
//        when(paymentTransferRepository.addNewPayee(payee)).thenThrow(new PayeeException("Payee exception message"));
//
//        ResponseEntity<String> responseEntity = myController.newPayee(payee);
//
//        assertEquals(HttpStatus.BAD_GATEWAY, responseEntity.getStatusCode());
//    }
//
//}
