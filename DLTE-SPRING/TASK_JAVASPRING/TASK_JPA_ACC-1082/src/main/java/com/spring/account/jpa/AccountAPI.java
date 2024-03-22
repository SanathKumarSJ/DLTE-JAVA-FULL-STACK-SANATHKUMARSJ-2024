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

    @PostMapping("/open")
    public Account apiSave(Account account){
        return accountService.callOpen(account);
    }
    @GetMapping("/")
    public List<Account> apiFindall(){
        return accountService.callDisplayAll();
    }

    @PutMapping("/update")
    public String apiUpdate(Account account){
        return accountService.callUpdate(account)+" updated";
    }
}
