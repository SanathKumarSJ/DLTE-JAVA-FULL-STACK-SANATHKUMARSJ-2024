package com.jdbctemp.template;

import com.jdbctemp.template.entity.Transaction;
import com.jdbctemp.template.service.TransactionException;
import com.jdbctemp.template.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//Rest controller
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    //APi's
    @PostMapping("/new")
    public String save(@RequestBody Transaction transaction){
        Transaction transaction1=null;
        try{
            transaction1=transactionService.apiSave(transaction);
        }
        catch (TransactionException t){
            throw new TransactionException();

        }
        return transaction1+" has transaction saved successfully";
    }

    // find transaction details by inputting sender name
    @GetMapping("/findby/{name}")
    public Optional<Transaction> findSender(@PathVariable("name") String name){
        return transactionService.apiFindBySender(name);
    }

    //find transaction details by specifnn the receiver
    @GetMapping("/findto/{nameto}")
    public Optional<Transaction> findReceiver(@PathVariable("nameto") String nameto){
        return transactionService.apiFindToReceiver(nameto);
    }

    //find transaction details by amount
    @GetMapping("/findamount/{amount}")
    public Optional<Transaction> findAmount(@PathVariable("amount") Double amount){
        return transactionService.apiFindAmount(amount);
    }
}
