package com.paymentdao.payment;

import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JdbcAddPayeeTest {
    @Mock
    ResourceBundle resourceBundle= ResourceBundle.getBundle("payee");
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    org.slf4j.Logger logger= LoggerFactory.getLogger( JdbcAddPayeeTest.class);


    @InjectMocks
    private PaymentTransferImplementation paymentTransferImplementation;

    @Test
    public void testAdd1() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987654321L;
        String payeeName = "John Doe";
        // Act
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);
        paymentTransferImplementation.deletePayee(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);

        // Assert
        verify(logger).info(anyString());

    }
    @Test
    public void testDeletePayeeNotFound() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987654321L;
        String payeeName = "John Doe";

        // Mock unsuccessful update (payee not found)
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(0);

        // Act and Assert
        try {
            paymentTransferImplementation.deletePayee(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);
        } catch (PayeeException e) {
            verify(logger).warn(anyString());
            assertEquals("Payee not found", e.getMessage());
        }
    }