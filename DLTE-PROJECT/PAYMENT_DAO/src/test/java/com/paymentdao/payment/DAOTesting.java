package com.paymentdao.payment;
import com.paymentdao.payment.configuration.ValidationConfiguration;
import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.exception.PayeeExistException;
import com.paymentdao.payment.exception.PayeeNotExistException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.MyBankOfficialsService;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DAOTesting {

    ResourceBundle resourceBundle= ResourceBundle.getBundle("payee");

    @InjectMocks
    MyBankOfficialsService myBankOfficialsService;

    @Mock
    JdbcTemplate jdbcTemplate;


    @InjectMocks
    PaymentTransferImplementation paymentTransferImplementation;



    @Test
    void testFindAllPayeeAccount() throws SQLSyntaxErrorException {
        Payee payee=new Payee();
        payee.setSenderAccountNumber(741741741741L);
        payee.setPayeeAccountNumber(789456321741L);
        payee.setPayeeName("Sanath");

        Payee payee2=new Payee();
        payee2.setSenderAccountNumber(741741741741L);
        payee2.setPayeeAccountNumber(789456111111L);
        payee2.setPayeeName("Rohith");

        Payee payee1=new Payee();
        payee1.setSenderAccountNumber(141741741741L);
        payee1.setPayeeAccountNumber(889456321741L);
        payee1.setPayeeName("Akash");

        List<Payee> expectedList = Stream.of(payee,payee1).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(Object[].class),
                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(expectedList);

        List<Payee> actualList = paymentTransferImplementation.findAllPayee(741741741741L);

        assertEquals(expectedList.size(), actualList.size());

    }

    @Test
    void testFindAllPayeeAccount2() throws SQLSyntaxErrorException {
        Payee payee=new Payee();
        payee.setPayeeId(100);
        payee.setSenderAccountNumber(741741741741L);
        payee.setPayeeAccountNumber(789456321741L);
        payee.setPayeeName("Sanath");

        Payee payee2=new Payee();
        payee2.setPayeeId(101);
        payee2.setSenderAccountNumber(741741741741L);
        payee2.setPayeeAccountNumber(789456111111L);
        payee2.setPayeeName("Rohith");

        List<Payee> expectedList = Stream.of(payee,payee2).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(Object[].class),
                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(expectedList);

        List<Payee> actualList = paymentTransferImplementation.findAllPayee(741741741741L);

        assertEquals(expectedList.get(0).getPayeeId()+expectedList.get(0).getSenderAccountNumber()+expectedList.get(0).getPayeeAccountNumber()+expectedList.get(0).getPayeeName(),actualList.get(0).getPayeeId()+actualList.get(0).getSenderAccountNumber()+actualList.get(0).getPayeeAccountNumber()+actualList.get(0).getPayeeName());

    }

    @Test
    void testPayeeException() {
        when(jdbcTemplate.query(anyString(), any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(Collections.emptyList());
        try {
            paymentTransferImplementation.findAllPayee(123434567897L);
        } catch (PayeeException | SQLSyntaxErrorException e) {
            assertEquals("No payee found with given account number -> "+123434567897L, e.getMessage());
        }

    }

    @Test
    public void testMapRow() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);

        // Define the behavior of the mock object
        Mockito.when(rs.getInt("PAYEE_ID")).thenReturn(1);
        Mockito.when(rs.getLong("SENDER_ACCOUNT_NUMBER")).thenReturn(123456789L);
        Mockito.when(rs.getLong("PAYEE_ACCOUNT_NUMBER")).thenReturn(987654321L);
        Mockito.when(rs.getString("PAYEE_NAME")).thenReturn("Test Payee");

        PaymentTransferImplementation.PayeeMapper mapper = new PaymentTransferImplementation.PayeeMapper();

        Payee payee = mapper.mapRow(rs, 1);

        assertEquals(1, payee.getPayeeId());
        assertEquals(123456789L, payee.getSenderAccountNumber());
        assertEquals(987654321L, payee.getPayeeAccountNumber());
        assertEquals("Test Payee", payee.getPayeeName());
    }



    @Test
    public void testAddPayee() {
        Payee payee1 = new Payee(500, 999956789654L, 789632564123L, "Sanath");

        lenient().when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        String result = paymentTransferImplementation.addNewPayee(payee1);

        assertEquals(resourceBundle.getString("insert.ok"),result);

    }

    @Test
    void testPayeeNotExistException() {
        Payee payee = new Payee();
        payee.setSenderAccountNumber(123456789L);
        payee.setPayeeAccountNumber(null);
        payee.setPayeeName("Sanath");

        doThrow(new DataAccessException("") {
            public Throwable getCause() {
                return new SQLException("", "", 20002);
            }
        }).when(jdbcTemplate).update(anyString(), any(), any(), any());

        assertThrows(PayeeNotExistException.class, () -> {
            paymentTransferImplementation.addNewPayee(payee);
        });

    }

    @Test
    void testPayeeException2() {
        Payee payee = new Payee();
        payee.setSenderAccountNumber(123456789L);
        payee.setPayeeAccountNumber(0L);
        payee.setPayeeName("Sanath");

        doThrow(new DataAccessException("") {
            public Throwable getCause() {
                return new SQLException("", "", 20003);
            }
        }).when(jdbcTemplate).update(anyString(), any(), any(), any());

        assertThrows(PayeeException.class, () -> {
            paymentTransferImplementation.addNewPayee(payee);
        });

    }
    @Test
    void testPayeeExistException() {
        Payee payee = new Payee();
        payee.setSenderAccountNumber(123456789L);
        payee.setPayeeAccountNumber(0L);
        payee.setPayeeName("Sanath");

        doThrow(new DataAccessException("") {
            public Throwable getCause() {
                return new SQLException("", "", 20004);
            }
        }).when(jdbcTemplate).update(anyString(), any(), any(), any());

        assertThrows(PayeeExistException.class, () -> {
            paymentTransferImplementation.addNewPayee(payee);
        });

    }


}


