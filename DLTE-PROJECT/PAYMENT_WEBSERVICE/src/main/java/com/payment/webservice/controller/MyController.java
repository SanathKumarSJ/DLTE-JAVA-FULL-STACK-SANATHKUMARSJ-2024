package com.payment.webservice.controller;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.CollusionException;
import com.paymentdao.payment.exception.InactiveException;
import com.paymentdao.payment.exception.NotExistException;
import com.paymentdao.payment.exception.PayeeException;
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
            @ApiResponse(responseCode = "409", description = "Payee account and sender's account cannot be same"),
            @ApiResponse(responseCode = "204", description = "No record exists"),
            @ApiResponse(responseCode = "403", description = "Sender account is inactive"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> newPayee(@Valid @RequestBody Payee payee) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        Customer customer = service.findByUsername(username);

        List<Long> senderAccountNumberList = service.getAccountList(customer.getCustomerId());

        if (senderAccountNumberList.contains(payee.getSenderAccountNumber())) {
            try {
                String check = paymentTransferRepository.addNewPayee(payee);
            } catch (PayeeException e) {
                logger.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
            } catch (CollusionException e) {
                logger.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            } catch (InactiveException e) {
                logger.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
            } catch (NotExistException e) {
                logger.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            logger.info(resourceBundle.getString("payee.ok"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("payee.ok"));
        }else{
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(payee.getSenderAccountNumber()+" "+resourceBundle.getString("no.access"));
        }
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