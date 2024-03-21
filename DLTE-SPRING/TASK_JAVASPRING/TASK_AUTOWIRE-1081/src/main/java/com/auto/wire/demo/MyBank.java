package com.auto.wire.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
//Performing autowire in the mode of field injection for HomeLoanImplementation
@Service
public class MyBank {
    @Autowired
    @Qualifier("home")  //homeloan
    private MyLoanInterface myLoanInterface;
    public List<Loan> callFindLoan(){
        List<Loan> injectedEntity=myLoanInterface.find();
        return injectedEntity;
    }
}
