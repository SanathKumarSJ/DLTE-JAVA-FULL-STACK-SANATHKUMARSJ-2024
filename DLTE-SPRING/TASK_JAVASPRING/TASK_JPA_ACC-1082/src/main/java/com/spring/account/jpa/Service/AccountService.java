package com.spring.account.jpa.Service;

import com.spring.account.jpa.entity.Account;
import com.spring.account.jpa.remote.AccountInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountInterface accountInterface;

//save method for creating new account as well as updating
    public Account callSave(Account account){
        return accountInterface.save(account);
    }
//displaying all methods
    public List<Account> callDisplayAll(){
        return (List<Account>) accountInterface.findAll();
    }
}
