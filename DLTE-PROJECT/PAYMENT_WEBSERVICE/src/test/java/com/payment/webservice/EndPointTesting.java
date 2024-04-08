package com.payment.webservice;

import com.payment.webservice.configuration.SoapPhase;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import services.payee.FetchAllPayeeRequest;
import services.payee.FetchAllPayeeResponse;
import services.payee.FindAllPayeeRequest;
import services.payee.FindAllPayeeResponse;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EndPointTesting {
    @MockBean
    private PaymentTransferRepository paymentService;

    @InjectMocks
    private SoapPhase soapPhase;

    @Test
    public void testFindAll() throws SQLSyntaxErrorException {
        List<com.paymentdao.payment.entity.Payee> payees = new ArrayList<>();
        com.paymentdao.payment.entity.Payee payee = new Payee();
        payee.setPayeeId(120);
        payee.setSenderAccountNumber(123456789741L);
        payee.setPayeeAccountNumber(987456789963L);
        payee.setPayeeName("Arururu");
        payees.add(payee);


        when(paymentService.findAllPayee(123456789741L)).thenReturn(payees);


        FindAllPayeeRequest request = new FindAllPayeeRequest();
        request.setSenderAccount(123456789741L);
        FindAllPayeeResponse response = soapPhase.listPayee(request);


        // checking the response is success or not
        assertEquals(200, response.getServiceStatus().getStatus());

    }

    @Test
    public void testFindAll2() throws SQLSyntaxErrorException {
        List<com.paymentdao.payment.entity.Payee> payees = new ArrayList<>();
        com.paymentdao.payment.entity.Payee payee = new Payee();
        payee.setPayeeId(121);
        payee.setSenderAccountNumber(123456789741L);
        payee.setPayeeAccountNumber(987456789987L);
        payee.setPayeeName("Sanath");
        payees.add(payee);


        when(paymentService.findAllPayee(123456789741L)).thenReturn(payees);


        FindAllPayeeRequest request = new FindAllPayeeRequest();
        request.setSenderAccount(123456789741L);
        FindAllPayeeResponse response = soapPhase.listPayee(request);


        // checking the response
        assertNotEquals(payees.get(0).getPayeeName(), response.getPayee().get(0).getPayeeName());

    }

    @Test
    public void testPayeeName() throws SQLSyntaxErrorException {
        List<com.paymentdao.payment.entity.Payee> payees = new ArrayList<>();
        com.paymentdao.payment.entity.Payee payee = new Payee();
        payee.setPayeeId(120);
        payee.setSenderAccountNumber(123456789L);
        payee.setPayeeAccountNumber(987456789L);
        payee.setPayeeName("Eeksha");
        payees.add(payee);


        when(paymentService.findAllPayee(123456789L)).thenReturn(payees);


        FindAllPayeeRequest request = new FindAllPayeeRequest();

        // passing the entity
        request.setSenderAccount(123456789L);
        FindAllPayeeResponse response = soapPhase.listPayee(request);


        //checking the payeeName in entity as well in response
        assertEquals(payee.getPayeeName(), response.getPayee().get(0).getPayeeName());
    }

    @Test
    public void testFetchAll() throws SQLSyntaxErrorException {
        com.paymentdao.payment.entity.Payee payee = new Payee();
        List<com.paymentdao.payment.entity.Payee> payees = new ArrayList<>();
        payee.setPayeeId(120);
        payee.setSenderAccountNumber(123456789L);
        payee.setPayeeAccountNumber(987456789L);
        payee.setPayeeName("Arururu");
        payees.add(payee);

        payee.setPayeeId(127);
        payee.setSenderAccountNumber(123456789741L);
        payee.setPayeeAccountNumber(987456789743L);
        payee.setPayeeName("Eeksha");
        payees.add(payee);

        payee.setPayeeId(129);
        payee.setSenderAccountNumber(123456789987L);
        payee.setPayeeAccountNumber(987654321000L);
        payee.setPayeeName("Sanath");
        payees.add(payee);

        when(paymentService.fetchAllPayeeDetails()).thenReturn(payees);

        FetchAllPayeeRequest request = new FetchAllPayeeRequest();

        FetchAllPayeeResponse response = soapPhase.fetchAll(request);


//checking the list size
        assertEquals("3", response.getPayee().size());

    }

    @Test
    public void testFetchAll2() throws SQLSyntaxErrorException {
        com.paymentdao.payment.entity.Payee payee = new Payee();
        List<com.paymentdao.payment.entity.Payee> payees = new ArrayList<>();
        payee.setPayeeId(120);
        payee.setSenderAccountNumber(123456789L);
        payee.setPayeeAccountNumber(987456789L);
        payee.setPayeeName("Arururu");
        payees.add(payee);

        payee.setPayeeId(127);
        payee.setSenderAccountNumber(123456789741L);
        payee.setPayeeAccountNumber(987456789743L);
        payee.setPayeeName("Eeksha");
        payees.add(payee);

        payee.setPayeeId(129);
        payee.setSenderAccountNumber(123456789987L);
        payee.setPayeeAccountNumber(987654321000L);
        payee.setPayeeName("Sanath");
        payees.add(payee);

        when(paymentService.fetchAllPayeeDetails()).thenReturn(payees);

        FetchAllPayeeRequest request = new FetchAllPayeeRequest();

        FetchAllPayeeResponse response = soapPhase.fetchAll(request);

        services.payee.Payee responsePayee1 = response.getPayee().get(0);
        assertEquals("Sanath", responsePayee1.getPayeeName());
    }
}