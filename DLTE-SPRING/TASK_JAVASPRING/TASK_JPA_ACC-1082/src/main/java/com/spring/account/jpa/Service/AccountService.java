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


    public Account callOpen(Account account){
        return accountInterface.save(account);
    }

    public List<Account> callDisplayAll(){
        return (List<Account>) accountInterface.findAll();
    }

    public Account callUpdate(Account account){
        return accountInterface.save(account);
    }
}
