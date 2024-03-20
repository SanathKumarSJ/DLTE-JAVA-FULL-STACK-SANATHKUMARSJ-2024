package org.test.soap;

import static org.junit.Assert.assertTrue;

import org.database.DatabaseTarget;
import org.database.StorageTarget;
import org.database.Transaction;
import org.database.UserServices;
import org.junit.Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import web.GroupTransaction;
import web.GroupUser;
import web.SOAPService;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {
    @Mock
    private UserServices services;
    private SOAPService soapService;

    @Before
    public void settingUp() {
        StorageTarget storageTarget = new DatabaseTarget();
        soapService = new SOAPService();
        services = new UserServices(storageTarget);
    }

    @Test
    public void testTransactionSave() {
        Transaction transaction = new Transaction("sanath", "debit", 8700.0, new java.sql.Date(2024, 7, 12));
        Transaction transaction2 = new Transaction("akshira", "debit", 18700.0, new java.sql.Date(2025, 9, 22));

        doNothing().when(services).callSaveTransaction(transaction2);
        soapService.createTransaction(transaction2);
//        verify(services,times(1)).callSaveTransaction(transaction2);
        verify(transaction2, times(1)).getTransactionAmount();
    }

    @Test
    public void testFindAll() {

        Transaction transaction = new Transaction("sanath", "debit", 8700.0, new java.sql.Date(2024, 7, 12));
        Transaction transaction2 = new Transaction("akshira", "debit", 18700.0, new java.sql.Date(2025, 9, 22));

        //adding the objects to expected list
        List<Transaction> expectedList1 = Stream.of(transaction, transaction2).collect(Collectors.toList());

        //calling find all transaction
        when(services.callFindAllTransaction()).thenReturn(expectedList1);

        //SOAP service read all transaction
        GroupTransaction groupOftransaction = soapService.readAllTransaction();

//        assertNull(groupOfCards);
        assertNotNull(groupOftransaction);
    }

    @Test
    public void testAllByUserName() {
        String Username = "sanath";
        Transaction transaction = new Transaction("sanath", "debit", 8700.0, new java.sql.Date(2024, 7, 12));
        Transaction transaction2 = new Transaction("akshira", "debit", 18700.0, new java.sql.Date(2025, 9, 22));
        List<Transaction> expectedList1 = Stream.of(transaction, transaction2).collect(Collectors.toList());

        //CallFindAllByUserName
        when(services.callFindByUsername(Username)).thenReturn(Collections.singletonList(transaction));

        // adding SOAP Service readAllByUsername
        GroupTransaction groupOftransaction = soapService.readAllByUsername(Username);

//        assertNull(groupOfCards);
        assertNotNull(groupOftransaction);
    }
}