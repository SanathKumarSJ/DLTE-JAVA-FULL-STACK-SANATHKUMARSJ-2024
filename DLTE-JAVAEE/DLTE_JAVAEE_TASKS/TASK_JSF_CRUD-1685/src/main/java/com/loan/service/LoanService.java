package com.loan.service;


import com.loan.entity.Loan;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@ViewScoped
public class LoanService {
    List<Loan> loanList;

    public LoanService() {
        loanList=new ArrayList<>();
        loanList.add(new Loan(741741852L,"10/8/2024","open","Sanath",887998995617L,25550.0));
        loanList.add(new Loan(741856165L,"8/11/2025","close","Akash",956456463738L,7800.0));
        loanList.add(new Loan(984565466L,"17/12/2026","close","Elroy",987479499738L,45000.0));
    }

    public void addLoan(Loan loan){
        loanList.add(loan);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Loan added",null));
    }

    public List<Loan> displayLoan(){
        return loanList.stream().filter(loan1->loan1.getLoanStatus().equals("close")).collect(Collectors.toList());
    }

    public void deleteLoan(Long loanNumber){
        loanList.removeIf(loan -> loan.getLoanNumber()==loanNumber);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Loan delete",null));
    }
}