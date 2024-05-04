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
            @ApiResponse(responseCode = "409", description = "Payee account and sender's account cannot be same"),
            @ApiResponse(responseCode = "409", description = "Payee record already exist"),
            @ApiResponse(responseCode = "204", description = "No record exists")
    })
    public ResponseEntity<String> newPayee(@Valid @RequestBody Payee payee) {

        String time= LocalDateTime.now().toString();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        Customer customer = service.findByUsername(username);

        List<Long> senderAccountNumberList = service.getAccountList(customer.getCustomerId());

        if (senderAccountNumberList.contains(payee.getSenderAccountNumber())) {
            try {
                String check = paymentTransferRepository.addNewPayee(payee);

            } catch (PayeeException e) {
                logger.error(e.getMessage());
                String responseBody= "Status Code: "+HttpStatus.CONFLICT.value()+" Error: "+HttpStatus.CONFLICT.getReasonPhrase()+"message: "+e.getMessage();
                return ResponseEntity.status(HttpStatus.OK).header("Content-Type","application/json").body(responseBody);
//                return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);

            } catch (PayeeExistException e) {
                logger.error(e.getMessage());
                String responseBody="Status Code: "+HttpStatus.CONFLICT.value()+" Error: "+HttpStatus.CONFLICT.getReasonPhrase()+"message: "+e.getMessage();
                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
//                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

            } catch (PayeeNotExistException e) {
                logger.error(e.getMessage());
                String responseBody="Status Code: "+HttpStatus.NOT_FOUND.value()+" Error: "+HttpStatus.NOT_FOUND.getReasonPhrase()+"message: "+e.getMessage();
                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

            }
            logger.info(resourceBundle.getString("payee.ok"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("payee.ok"));
        }else{
            String responseBody="Status Code: "+HttpStatus.FORBIDDEN.value()+" Error: "+HttpStatus.FORBIDDEN.getReasonPhrase()+"message: "+resourceBundle.getString("no.access");
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resourceBundle.getString("no.access"));
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

    @GetMapping("/getUsername")
    public String getName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer=service.findByUsername(username);
        return customer.getUserName();
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