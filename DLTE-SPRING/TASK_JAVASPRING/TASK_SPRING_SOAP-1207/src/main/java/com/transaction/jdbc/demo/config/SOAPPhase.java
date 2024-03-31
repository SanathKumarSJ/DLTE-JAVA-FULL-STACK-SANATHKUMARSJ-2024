package com.transaction.jdbc.demo.config;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.fasterxml.jackson.databind.BeanProperty;
import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;
import com.transaction.jdbc.demo.DAO.Transaction;
import com.transaction.jdbc.demo.DAO.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.transaction.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SOAPPhase {
    private final String url="http://transaction.services";
    @Autowired
    private TransactionService transactionService;

    @PreAuthorize("hasAuthority('admin')")
    @PayloadRoot(namespace = url,localPart = "newTransactionRequest")
    @ResponsePayload
    public services.transaction.NewTransactionResponse addNewTransaction(@RequestPayload services.transaction.NewTransactionRequest newTransactionRequest){

        //Transaction Response
        services.transaction.NewTransactionResponse newTransactionResponse=new services.transaction.NewTransactionResponse();

        //service status
        services.transaction.ServiceStatus serviceStatus=new services.transaction.ServiceStatus();


        services.transaction.Transaction actual=newTransactionRequest.getTransaction();

        //Transaction entity
        Transaction transaction=new Transaction();

        BeanUtils.copyProperties(actual,transaction);

       // ------------------------Save method ----------------------
        //Calling transaction save method
        transaction=transactionService.apiSave(transaction);
        if(transaction!=null){
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(transaction,actual);
            newTransactionResponse.setTransaction(actual);
            serviceStatus.setMessage(actual.getTransactionId()+" has inserted");
        }
        else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(actual.getTransactionId()+" hasn't inserted");
        }
        newTransactionResponse.setServiceStatus(serviceStatus);
        return newTransactionResponse;
    }

    //-----------------------findBySender----------------
    @PreAuthorize("hasAuthority('customer')")
    @PayloadRoot(namespace = url,localPart = "findBySenderRequest")
    @ResponsePayload
    public FindBySenderResponse findBySenderRequest(@RequestPayload FindBySenderRequest findBySenderRequest){
        FindBySenderResponse findBySenderResponse=new FindBySenderResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.transaction.Transaction> transactList=new ArrayList<>();
        List<Transaction> transatListTwo=transactionService.apiFindBySender(findBySenderRequest.getSenderName());


        Iterator<Transaction> iterator=transatListTwo.iterator();
        while (iterator.hasNext()){
            services.transaction.Transaction currentTransactions=new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            transactList.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions available");
        findBySenderResponse.setServiceStatus(serviceStatus);
        findBySenderResponse.getTransaction().addAll(transactList);
        return findBySenderResponse;
    }

    @PreAuthorize("hasAuthority('customer')")
    @PayloadRoot(namespace = url,localPart = "findByReceiveRequest")
    @ResponsePayload
    public FindByReceiveResponse findByReceiveResponse(@RequestPayload FindByReceiveRequest findByReceiveRequest){
        FindByReceiveResponse findByReceiveResponse=new FindByReceiveResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.transaction.Transaction> transactList=new ArrayList<>();
        List<Transaction> transatListTwo=transactionService.apiFindToReceiver(findByReceiveRequest.getReceiveName());


        Iterator<Transaction> iterator=transatListTwo.iterator();
        while (iterator.hasNext()){
            services.transaction.Transaction currentTransactions=new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            transactList.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions available");
        findByReceiveResponse.setServiceStatus(serviceStatus);
        findByReceiveResponse.getTransaction().addAll(transactList);
        return findByReceiveResponse;
    }

    @PreAuthorize("hasAuthority('customer')")
    @PayloadRoot(namespace = url,localPart = "findByAmountRequest")
    @ResponsePayload
    public FindByAmountResponse findByAmountRequest(@RequestPayload FindByAmountRequest findByAmountRequest) {
        FindByAmountResponse findByAmountResponse = new FindByAmountResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> transactList = new ArrayList<>();
        List<Transaction> transatListTwo = transactionService.apiFindAmount(findByAmountRequest.getAmount());

        Iterator<Transaction> iterator = transatListTwo.iterator();
        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransactions = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransactions);
            transactList.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions available");
        findByAmountResponse.setServiceStatus(serviceStatus);
        findByAmountResponse.getTransaction().addAll(transactList);
        return findByAmountResponse;
    }

    @PreAuthorize("hasAnyAuthority('manager','admin')")
    //---------------------updateRemarks-------------
    @PayloadRoot(namespace=url, localPart = "updateRemarksRequest")
    @ResponsePayload
    public UpdateRemarkResponse updateRemark(@RequestBody UpdateRemarksRequest updateRemarksRequest){
        UpdateRemarkResponse updateRemarkResponse=new UpdateRemarkResponse();
        ServiceStatus serviceStatus=new ServiceStatus();

        services.transaction.Transaction actual=updateRemarksRequest.getTransaction();

        //transaction dao
        Transaction transaction=new Transaction();

        BeanUtils.copyProperties(actual,transaction);

        BeanUtils.copyProperties(actual,transaction);

        //Calling transaction update method
        transaction=transactionService.updateOnRemark(transaction);
        if(transaction!=null){
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(transaction,actual);
            updateRemarkResponse.setTransaction(actual);
            serviceStatus.setMessage(actual.getTransactionId()+" has updated");
        }
        else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(actual.getTransactionId()+" hasn't updated");
        }
        updateRemarkResponse.setServiceStatus(serviceStatus);
        return updateRemarkResponse;
    }


    //---------------Delete on given date
    @PreAuthorize("hasAuthority('admin')")
    @PayloadRoot(namespace = url, localPart = "RemoveRequest")
    @ResponsePayload
    public RemoveResponse removeOnDate(@RequestBody RemoveRequest removeRequest) throws DatatypeConfigurationException {
        RemoveResponse removeResponse=new RemoveResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
//        String check=transactionService.removeOnDate()


        Date startDate=removeRequest.getStartDate().toGregorianCalendar().getTime();
        Date endDate=removeRequest.getEndDate().toGregorianCalendar().getTime();
        String result = transactionService.removeOnDate(startDate,endDate);
//        if (result.equals("removed")) {
            serviceStatus.setStatus("Transaction record removed");
//        } else {
            serviceStatus.setStatus("No transaction record found");
//        }
        serviceStatus.setMessage(result);

        removeResponse.setServiceStatus(serviceStatus);

        return removeResponse;
    }

    }