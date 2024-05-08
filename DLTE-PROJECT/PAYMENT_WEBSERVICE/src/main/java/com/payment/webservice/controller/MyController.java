package com.payment.webservice.controller;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.exception.PayeeExistException;
import com.paymentdao.payment.exception.PayeeNotExistException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.MyBankOfficialsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;


@RestController
@ComponentScan("com.paymentdao.payment")
@RequestMapping("/pay")
public class MyController {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("payee");
    org.slf4j.Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    PaymentTransferRepository paymentTransferRepository;

    @Autowired
    MyBankOfficialsService service;

    @Operation(summary = "This is used for adding new Payee")
    @PostMapping("/add")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payee Inserted successfully"),
            @ApiResponse(responseCode = "EXC001", description = "Payee account and sender's account cannot be same"),
            @ApiResponse(responseCode = "EXC002", description = "Payee account already exists with sender account"),
            @ApiResponse(responseCode = "EXC003", description = "Payee account doesn't exist"),
            @ApiResponse(responseCode = "EXC004", description = "Access denied for this account number")
    })
    public ResponseEntity<String> newPayee(@Valid @RequestBody Payee payee) {
        logger.info("Sender Account Number - " + payee.getSenderAccountNumber() +
                ", Payee Account Number - " + payee.getPayeeAccountNumber() +
                ", Payee Name - " + payee.getPayeeName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        Customer customer = service.findByUsername(username);

        List<Long> senderAccountNumberList = service.getAccountList(customer.getCustomerId());

        if (senderAccountNumberList.contains(payee.getSenderAccountNumber())) {
            try {
                String check = paymentTransferRepository.addNewPayee(payee);
                logger.info(check);

            } catch (PayeeException e) {
                logger.error(resourceBundle.getString("payee.error.one")+e.getMessage());
                String responseBody=resourceBundle.getString("payee.error.one")+e.getMessage();
                return ResponseEntity.status(HttpStatus.OK).body(responseBody);

            } catch (PayeeExistException e) {
                logger.error(resourceBundle.getString("payee.error.two")+e.getMessage());
                String responseBody=resourceBundle.getString("payee.error.two")+e.getMessage();
                return ResponseEntity.status(HttpStatus.OK).body(responseBody);

            } catch (PayeeNotExistException e) {
                logger.error(resourceBundle.getString("payee.error.three")+e.getMessage());
                String responseBody=resourceBundle.getString("payee.error.three")+e.getMessage();
                return ResponseEntity.status(HttpStatus.OK).body(responseBody);

            }
            logger.info(resourceBundle.getString("payee.ok"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("payee.ok"));
        }else{
            String responseBody=resourceBundle.getString("payee.error.four")+resourceBundle.getString("no.access");
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
    }


    @GetMapping("/getAccount")
    public List<Long> getAccountNumber(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer=service.findByUsername(username);
        List<Long> senderAccountNumber=service.getAccountList(customer.getCustomerId());
        return senderAccountNumber;
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}