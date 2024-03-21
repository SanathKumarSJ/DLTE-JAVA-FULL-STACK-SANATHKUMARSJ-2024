package com.auto.wire.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("home")
public class HomeLoan implements MyLoanInterface{

    @Override
    public List<Loan> find() {
        List<Loan> homeList=new ArrayList<>();
        for(Loan each:loansList){
            if(each.getLoanType().equalsIgnoreCase("home")){
                homeList.add(each);
            }
        }
        return homeList;
    }
}
