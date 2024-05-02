package com.payment.webservice.configuration;
import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.MyBankOfficialsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.payee.*;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Endpoint
@ComponentScan("com.paymentdao.payment.service")
public class SoapPhase {

    @Autowired
    MyBankOfficialsService service;

    private final String url = "http://payee.services";

    ResourceBundle resourceBundle = ResourceBundle.getBundle("payee");
    org.slf4j.Logger logger = LoggerFactory.getLogger(SoapPhase.class);

    @Autowired
    private PaymentTransferRepository paymentTransferImplementation;

    @PayloadRoot(namespace = url, localPart = "findAllPayeeRequest")
    @ResponsePayload
    public FindAllPayeeResponse listPayee(@RequestPayload FindAllPayeeRequest findAllPayeeRequest) throws SQLSyntaxErrorException {

        List<Payee> payees = new ArrayList<>();

        FindAllPayeeResponse findAllPayeeResponse = new FindAllPayeeResponse();

        ServiceStatus serviceStatus = new ServiceStatus();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        Customer customer = service.findByUsername(username);

        List<Long> senderAccountNumberList = service.getAccountList(customer.getCustomerId());

        if (senderAccountNumberList.contains(findAllPayeeRequest.getSenderAccount())) {
            try {
                List<com.paymentdao.payment.entity.Payee> daoPayee = paymentTransferImplementation.findAllPayee(findAllPayeeRequest.getSenderAccount());
                daoPayee.forEach(each -> {
                    Payee currentPayee = new Payee();
                    BeanUtils.copyProperties(each, currentPayee);
                    payees.add(currentPayee);
                });

                serviceStatus.setStatus(HttpStatus.OK.value());

                serviceStatus.setMessage(resourceBundle.getString("payee.fetched"));

                logger.info(resourceBundle.getString("payee.fetched"));

                findAllPayeeResponse.setServiceStatus(serviceStatus);

                findAllPayeeResponse.getPayee().addAll(payees);

                return findAllPayeeResponse;

            } catch (PayeeException payeeEx) {
                serviceStatus.setStatus(HttpStatus.NOT_FOUND.value());
                serviceStatus.setMessage(payeeEx.getMessage());
                findAllPayeeResponse.setServiceStatus(serviceStatus);
                return findAllPayeeResponse;
            }
        } else {
            serviceStatus.setStatus(HttpStatus.FORBIDDEN.value());

            serviceStatus.setMessage(resourceBundle.getString("no.access"));

            logger.warn(resourceBundle.getString("no.access"));

            findAllPayeeResponse.setServiceStatus(serviceStatus);

            findAllPayeeResponse.getPayee().addAll(payees);

            return findAllPayeeResponse;
        }
    }
}