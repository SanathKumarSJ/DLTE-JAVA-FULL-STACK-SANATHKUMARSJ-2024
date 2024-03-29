package com.auto.wire.demo;

import java.util.ArrayList;
import java.util.List;

public class PersonalLoan implements MyLoanInterface {
    @Override
    public List<Loan> find() {
        List<Loan> personalList=new ArrayList<>();
        for(Loan each:loansList){
            if(each.getLoanType().equalsIgnoreCase("personal")){
                personalList.add(each);
            }
        }
        return personalList;
    }
}
