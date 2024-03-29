package com.transaction.jdbc.demo;

import com.transaction.jdbc.demo.DAO.Transaction;
import com.transaction.jdbc.demo.DAO.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DemoApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TransactionService transactionService;


    //------------Testing New Transaction---------------------
    @Test
    public void newTransaction() {
        Transaction transaction = new Transaction(99157284L, new Date(2024, 02, 10), "Komal", "Jaggesh", 98000.0, "Bill");
        Transaction transaction1 = new Transaction(785782585285L, new Date(2024, 12, 02), "Sanath", "Mahesh", 8500.0, "Family");


        Transaction actual = transactionService.apiSave(transaction);

        // check the saved transaction has the same name
        assertEquals(transaction.getTransactionBy(), actual.getTransactionBy()); //pass

        //checking transaction1 saved or not passed transaction
//        assertSame(transaction1, actual);  //fail
    }

    //----------------------Testing TransactionBy-------------------------
    @Test
    public void testBySender() {
        String userName = "Sanath";
        Transaction transaction1 = new Transaction(785782585285L, new Date(2024, 12, 02), "Sanath", "Mahesh", 8500.0, "Family");
        Transaction transaction2 = new Transaction(42572575L, new Date(2024, 05, 03), "Tejas", "Pramith", 9600.0, "Friend");

        List<Transaction> expectedList = Stream.of(transaction1, transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class))).thenReturn(expectedList);

        List<Transaction> check = transactionService.apiFindBySender("sanath");
        assertEquals(expectedList, check);
    }


    //----------------------Testing TransactionTo-------------------------

    @Test
    void testByReceiver(){
        String userName = "Pramith";
        Transaction transaction1 = new Transaction(785782585285L, new Date(2024, 12, 02), "Sanath", "Mahesh", 8500.0, "Family");
        Transaction transaction2 = new Transaction(42572575L, new Date(2024, 05, 03), "Tejas", "Pramith", 9600.0, "Friend");

        List<Transaction> expectedList = Stream.of(transaction1, transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class))).thenReturn(expectedList);

        // calling findby receiver service
        List<Transaction> check = transactionService.apiFindToReceiver(userName);


        assertNull(expectedList);// check if the list is null or not
    }



    //----------------------Testing Testing by Amount-------------------------
    @Test
    void testGetByAmount(){
        Double amount=9600.0;
        Transaction transaction1 = new Transaction(785782585285L, new Date(2024, 12, 02), "Sanath", "Mahesh", 8500.0, "Family");
        Transaction transaction2 = new Transaction(42572575L, new Date(2024, 05, 03), "Tejas", "Pramith", 9600.0, "Friend");

        List<Transaction> expectedList = Stream.of(transaction1, transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class))).thenReturn(expectedList);

        // calling find amount service
        List<Transaction> actual = transactionService.apiFindAmount(amount);

        //checking expected list is same as actual list
        assertSame(expectedList,actual);//fail

        // check whether the amount exists or not
        assertEquals(amount,actual.get(0).getTransactionAmount());
    }

    //-------------------Update Remarks--------------------
    @Test
    void testRemarksUpdate(){
        Transaction transaction1 = new Transaction(785782585285L, new Date(2024, 12, 02), "Sanath", "Mahesh", 8500.0, "Family");
        Transaction transaction2 = new Transaction(42572575L, new Date(2024, 05, 03), "Tejas", "Pramith", 9600.0, "Friend");
        Transaction transaction3 = new Transaction(42572575L, new Date(2024, 05, 03), "Tejas", "Pramith", 9600.0, "Emergency");

        List<Transaction> expectedList = Stream.of(transaction1, transaction3).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class))).thenReturn(expectedList);

        Transaction transaction=transactionService.updateOnRemark(expectedList.get(1));

        //check whether updated and input objects or same or not
        assertEquals(expectedList.get(1).toString(),transaction.toString()); //pass

        //check whether transaction1 and resultant object same or not if not same --> updated
        assertNotEquals(transaction1.getTransactionRemarks(),transaction.getTransactionRemarks()); //pass

    }

    //-------------------------Test deletion within the given date range----------------------
    @Test
    void testRemoveBWDate() {
        Transaction transaction1 = new Transaction(785782585285L, new Date(2024, 12, 02), "Sanath", "Mahesh", 8500.0, "Family");
        Transaction transaction2 = new Transaction(42572575L, new Date(2024, 05, 03), "Tejas", "Pramith", 9600.0, "Friend");
        Transaction transaction3 = new Transaction(42572575L, new Date(2024, 05, 03), "Tejas", "Pramith", 9600.0, "Emergency");

        List<Transaction> expectedList = Stream.of(transaction1, transaction3).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class))).thenReturn(expectedList);

        Date startDate =new Date(2024,12,01);
        Date endDate =new Date(2024,12,03);
        String result = transactionService.removeOnDate(startDate, endDate);

        // returned string equals to "removed" then the record removed
        assertEquals("removed", result);
    }
}