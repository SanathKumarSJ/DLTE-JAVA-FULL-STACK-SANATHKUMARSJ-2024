package com.spring.account.jpa;

import com.spring.account.jpa.Service.AccountService;
import com.spring.account.jpa.entity.Account;
import com.spring.account.jpa.remote.AccountInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountAPI {
    @Autowired
    AccountService accountService;

    //post mapping for adding new account
    @PostMapping("/open")
    public String apiSave(@RequestBody Account account){
        return accountService.callSave(account)+ " added successfully";
    }

    //get mapping for displaying all account
    @GetMapping("/")
    public List<Account> apiFindall(){
        return accountService.callDisplayAll();
    }

    //put mapping for updating account
    @PutMapping("/update")
    public String apiUpdate(@RequestBody Account account){
        return accountService.callSave(account)+" updated";
    }
}
