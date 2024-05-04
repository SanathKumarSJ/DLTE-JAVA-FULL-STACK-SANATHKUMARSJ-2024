//package com.paymentdao.payment;
//
//import com.paymentdao.payment.entity.Payee;
//import com.paymentdao.payment.service.PaymentTransferImplementation;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.ResourceBundle;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//
//@SpringBootTest
//public class JdbcAddPayeeTest {
//    ResourceBundle resourceBundle= ResourceBundle.getBundle("payee");
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @Mock
//    org.slf4j.Logger logger = LoggerFactory.getLogger(JdbcAddPayeeTest.class);
//
//
//    @InjectMocks
//    private PaymentTransferImplementation paymentTransferImplementation;
//
//    @Test
//    public void testAdd1() {
//        Payee payee1 = new Payee(500, 999956789654L, 789632564123L, "Sanath");
//        Payee payee2 = new Payee(501, 999956789654L, 963258741236L, "Arundhathi");
//        Payee payee3 = new Payee(502, 889956789654L, 632589741233L, "Eeksha");
//
//        lenient().when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
//        String result = paymentTransferImplementation.addNewPayee(payee1);
//
//
//        assertEquals("Payee record inserted successfully", result);
//
//    }
//
//    @Test
//    void testAddNewPayeeSuccess2() {
//        Payee payee = new Payee(100,999956789654L, 789632564123L, "Sanath"); // create a valid Payee object
//        when(jdbcTemplate.update(anyString(), eq(payee.getSenderAccountNumber()), eq(payee.getPayeeAccountNumber()), eq(payee.getPayeeName()))).thenReturn(1);
//
//        String result = paymentTransferImplementation.addNewPayee(payee);
//
////        Only one time invocation done
//        verify(jdbcTemplate, times(1)).update(anyString(), eq(payee.getSenderAccountNumber()), eq(payee.getPayeeAccountNumber()), eq(payee.getPayeeName()));
//    }
//
//    @Test
//    public void testAdd2() {
//         Payee payee = new Payee(502, 889956789654L, 632589741233L, "Eeksha");
//
//        lenient().when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
//        String result = paymentTransferImplementation.addNewPayee(payee);
//
//        //checking the result and resource bundle
//        assertEquals(resourceBundle.getString("insert.ok"),result);
//
//    }
//
//    @Test
//    public void testAdd3() {
//        Payee payee = new Payee(512, 777956789654L, 632589741233L, "Eeksha");
//
//        lenient().when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
//        String result = paymentTransferImplementation.addNewPayee(payee);
//
//        //checking the result and resource bundle
//        assertEquals(resourceBundle.getString("insert.ok"),result);
//
//    }
//}
