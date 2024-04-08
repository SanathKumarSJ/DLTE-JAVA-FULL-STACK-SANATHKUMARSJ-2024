package com.paymentdao.payment;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;


import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DAOTesting {

    @Mock
    JdbcTemplate jdbcTemplate;
    @InjectMocks
    PaymentTransferImplementation paymentTransferImplementation;


    // mvn test-compile test
   @Test
    void testFindAll() throws SQLSyntaxErrorException {
        Payee payee1=new Payee(500,999956789654L,789632564123L,"Sanath");
        Payee payee2=new Payee(501,897423123564L,963258741236L,"Arundhathi");
        Payee payee3=new Payee(502,889956789654L,632589741233L,"Eeksha");
        List<Payee> payees= Stream.of(payee1,payee2,payee3).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(),any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);


        // Call the method under test
        List<Payee> actualList = paymentTransferImplementation.fetchAllPayeeDetails();

        //checking the list size
        assertSame(3,actualList.size());

    }

    @Test
    void testFindAll2() throws SQLSyntaxErrorException {
        Payee payee1=new Payee(500,999956789654L,789632564123L,"Sanath");
        Payee payee2=new Payee(501,897423123564L,963258741236L,"Arundhathi");
        Payee payee3=new Payee(502,889956789654L,632589741233L,"Eeksha");
        List<Payee> payees= Stream.of(payee1,payee2,payee3).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(),
                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);


        // Call the method under test
        List<Payee> actualList = paymentTransferImplementation.fetchAllPayeeDetails();

//        checking with the sender payee account number in the respective index
        assertEquals(8974231235264L, actualList.get(1).getSenderAccountNumber());//fails

    }

    @Test
    void testFindAllBasedOnAccount() throws SQLSyntaxErrorException {
        Payee payee1=new Payee(500,999956789654L,789632564123L,"Sanath");
        Payee payee2=new Payee(501,999956789654L,963258741236L,"Arundhathi");
        Payee payee3=new Payee(502,889956789654L,632589741233L,"Eeksha");
        List<Payee> payees= Stream.of(payee1,payee2,payee3).collect(Collectors.toList());


        when(jdbcTemplate.query(anyString(),any(Object[].class),
                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);
//        given(jdbcTemplate.query(anyString(),any(Object[].class),
//                any(PaymentTransferImplementation.PayeeMapper.class))).willReturn(payees);

        // Calling the findAllPayee and passing the sender account number
        List<Payee> actualList = paymentTransferImplementation.findAllPayee(999956789654L);

        //checking the actual list we two sender account is same so the length should be same
//        assertEquals(2,actualList.size());

        //fails because 0 index im testing has PayeeAccountNumber 789632564123
        assertEquals(963258741236L, actualList.get(0).getPayeeAccountNumber()); //fails
    }


}

