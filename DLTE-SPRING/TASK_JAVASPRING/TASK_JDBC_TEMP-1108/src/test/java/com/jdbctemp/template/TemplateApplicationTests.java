package com.jdbctemp.template;

import com.jdbctemp.template.entity.Transaction;
import com.jdbctemp.template.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TemplateApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testNewTransaction(){
        Transaction transaction1=new Transaction(78965412123L,new java.util.Date(2024,02,20),"Rohith","Virat",78000.0,"Bill");
        Transaction transaction2=new Transaction(54656412123L,new Date(2024,03,12),"Mahesh","Pramith",88000.0,"friend");

        //updating
        lenient().when(jdbcTemplate.update(anyString(),any(Object[].class))).thenReturn(1);
        //calling save method
        Transaction actualTransaction=transactionService.apiSave(transaction1);

        //checking the saved transaction matches with the input
        assertEquals(actualTransaction,transaction1);
    }

    @Test
    void testTransactionBy(){
        Transaction transaction1=new Transaction(78965412123L,new java.util.Date(2024,02,20),"Rohith","Virat",78000.0,"Bill");
        Transaction transaction2=new Transaction(54656412123L,new Date(2024,03,12),"Mahesh","Pramith",88000.0,"friend");

        when(jdbcTemplate.queryForObject(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(transaction2);

        Optional<Transaction> actual = transactionService.apiFindToReceiver("Rohith");

        //checking for equal
        assertEquals(transaction1.getTransactionBy(),actual.get().getTransactionBy());
    }

    @Test
    void testTransactionTO(){
        Transaction transaction1=new Transaction(78965412123L,new java.util.Date(2024,02,20),"Rohith","Virat",78000.0,"Bill");
        Transaction transaction2=new Transaction(54656412123L,new Date(2024,03,12),"Mahesh","Pramith",88000.0,"friend");

        when(jdbcTemplate.queryForObject(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(transaction1);

        Optional<Transaction> actual = transactionService.apiFindToReceiver("Pramith");

        //checking for equal
        assertEquals(transaction1.getTransactionTo(),actual.get().getTransactionTo());
    }


    @Test
    void testTransactionAmount(){
        Transaction transaction1=new Transaction(78965412123L,new java.util.Date(2024,02,20),"Rohith","Virat",78000.0,"Bill");
        Transaction transaction2=new Transaction(54656412123L,new Date(2024,03,12),"Mahesh","Pramith",88000.0,"friend");

//        List<Transaction> expectedList= Stream.of(transaction1,transaction2).collect(Collectors.toList());

        when(jdbcTemplate.queryForObject(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(transaction1);

        Optional<Transaction> actual = transactionService.apiFindAmount(78000.0);
        // check for not equal
        assertNotEquals(transaction1.getTransactionAmount(),actual.get().getTransactionAmount());
    }
    }

