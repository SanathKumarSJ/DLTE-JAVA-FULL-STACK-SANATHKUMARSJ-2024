package com.jpa.hqlsql.jpahqlsql.service;

import com.jpa.hqlsql.jpahqlsql.entity.Transaction_JPA;
import com.jpa.hqlsql.jpahqlsql.repository.MyJPARepo;
import com.jpa.hqlsql.jpahqlsql.repository.MyRepoNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactioService {

    @Autowired
    MyJPARepo myJPARepo;

    @Autowired
    MyRepoNew myRepoNew;

    public Transaction_JPA callsave(Transaction_JPA transaction){
        return myRepoNew.save(transaction);
    }

    public List<Transaction_JPA> callFindByNameType(String name,String type){
        return myJPARepo.transactionType(name,type);
    }

    public List<Transaction_JPA> callFindAmount(Double amount, Double amount2){
        return myJPARepo.amountRange(amount,amount2);
    }
}
