//package com.transaction.jdbc.demo;
//
//import com.transaction.jdbc.demo.DAO.Transaction;
//import com.transaction.jdbc.demo.DAO.TransactionService;
//import com.transaction.jdbc.demo.config.SOAPPhase;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.BeanUtils;
//import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import services.transaction.*;
//import sun.util.calendar.LocalGregorianCalendar;
//
//
//import javax.xml.datatype.DatatypeConfigurationException;
//import javax.xml.datatype.DatatypeFactory;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.List;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class EndPointTest {
//    @MockBean
//    private TransactionService transactionService;
//
//    @InjectMocks
//    private SOAPPhase soapPhase;
//
//    @Test
//    public void testNewTraxn() throws DatatypeConfigurationException {
//        NewTransactionRequest newTransactionRequest = new NewTransactionRequest();
//        services.transaction.Transaction transaction = new services.transaction.Transaction();
//        transaction.setTransactionId(578200L);
//        transaction.setTransactionDate(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2024,12,03,12));
//        transaction.setTransactionBy("Sanath");
//        transaction.setTransactionTo("Mahesh");
//        transaction.setTransactionAmount(25800.0);
//        transaction.setTransactionRemarks("bill");
//        newTransactionRequest.setTransaction(transaction);
//        System.out.println(newTransactionRequest.getTransaction());
//        NewTransactionResponse response = soapPhase.addNewTransaction(newTransactionRequest);
//        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
//    }
//
//    @Test
//    public void testSender() {
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(new Transaction(65455634655L, new Date(2024,03,12), "Sanath", "Pramith", 98000.0, "Friend"));
//        when(transactionService.apiFindBySender("Sanath")).thenReturn( transactions);
//        FindBySenderRequest request = new FindBySenderRequest();
//        request.setSenderName("Sanath");
//        FindBySenderResponse response = soapPhase.findBySenderRequest(request);
//        // checking the response is success or not
//        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
//        assertEquals(transactions.get(0).getTransactionBy(),request.getSenderName());
//    }
//
//    @Test
//    public void testReceiver() {
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(new Transaction(46846465L, new Date(2024,03,12), "Sanath", "Pramith", 98000.0, "Friend"));
//        when(transactionService.apiFindToReceiver("Pramith")).thenReturn( transactions);
//        FindByReceiveRequest request = new FindByReceiveRequest();
//        request.setReceiveName("Pramith");
//        FindByReceiveResponse response = soapPhase.findByReceiveResponse(request);
//        // checking the response is success or not
//        assertNotEquals("SUCCESS", response.getServiceStatus().getStatus());
//        assertEquals(transactions.get(0).getTransactionBy(),request.getReceiveName());
//    }
//
//    @Test
//    public void testAmount() {
//            List<Transaction> transactions = new ArrayList<>();
//            transactions.add(new Transaction(46846465L, new Date(2024,03,12), "Sanath", "Pramith", 98000.0, "Friend"));
//            when(transactionService.apiFindAmount(980000.0)).thenReturn(transactions);
//            FindByAmountRequest request = new FindByAmountRequest();
//            request.setAmount(98000.0);
//            FindByAmountResponse response = soapPhase.findByAmountRequest(request);
//            // checking the response is success or not
//            assertNotEquals("SUCCESS", response.getServiceStatus().getStatus());
//        }
//
//
//    @Test
//    public void testUpdate() throws DatatypeConfigurationException {
//        Transaction updateTransaction = new Transaction();
//        //setting the values
//        updateTransaction.setTransactionId(857858500L);
//        updateTransaction.setTransactionDate(new Date(2024,02,22));
//        updateTransaction.setTransactionBy("rohith");
//        updateTransaction.setTransactionTo("rakesh");
//        updateTransaction.setTransactionAmount(19800.0);
//        updateTransaction.setTransactionRemarks("family");
//        when(transactionService.updateOnRemark(any(Transaction.class))).thenReturn(updateTransaction);
//        UpdateRemarksRequest request = new UpdateRemarksRequest();
//        services.transaction.Transaction transaction = new services.transaction.Transaction();
//        transaction.setTransactionId(15000L);
//        transaction.setTransactionDate( DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2024,12,03,12));
//        transaction.setTransactionBy("rohith");
//        transaction.setTransactionTo("rakesh");
//        transaction.setTransactionAmount(19800.0);
//        transaction.setTransactionRemarks("friend");
//        request.setTransaction(transaction);
//        UpdateRemarkResponse response = soapPhase.updateRemark(request);
//        //checking for status
//        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
//        // checking before and after remark value
//        assertEquals(updateTransaction.getTransactionRemarks(), response.getTransaction().getTransactionRemarks());
//    }
//
//    @Test
//    public void testRemoveTransactionBetweenDates() throws DatatypeConfigurationException {
//        Transaction transaction = new Transaction(99157284L, new Date(2024, 02, 10), "Komal", "Jaggesh", 98000.0, "Bill");
//        Transaction transaction1 = new Transaction(785782585285L, new Date(2024, 12, 02), "Sanath", "Mahesh", 8500.0, "Family");
//        Transaction actual = transactionService.apiSave(transaction);
//       Date date=new Date(2024,02,01);
//       Date date1=new Date(2024,03,03);
//        when(transactionService.removeOnDate(date, date1)).thenReturn("removed");
//        RemoveRequest request = new RemoveRequest();
//        request.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2024,12,01,12));
//        request.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2024,12,30,12));
//        RemoveResponse response = soapPhase.removeOnDate(request);
//        assertEquals("Transaction record removed", response.getServiceStatus().getStatus());
//        assertNotEquals("removed",response.getServiceStatus().getMessage());
//
//    }
//
//}
