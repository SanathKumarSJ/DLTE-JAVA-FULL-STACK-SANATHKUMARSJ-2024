package com.auto.wire.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface MyLoanInterface {
    //initialising the loanentity values
    List<Loan> loansList= Stream.of(new Loan(987455645L,25000L,"close","personal","Sanath",9745896321L),new Loan(7415855454545L,78000L,"open","personal","Rohith",9845564566666L),new Loan(78798454165L,96000L,"open","home","Mahesh",84654656664564L)).collect(Collectors.toList());
    List<Loan> find();//find method
}
