package org.loantest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public interface MyBank {
    // abstract methods
    //ArrayList<Loan> loanInfo=new ArrayList<>();
    void checkAvailableLoan() throws IOException, ClassNotFoundException;
    void getCheckClosedLoan() throws IOException, ClassNotFoundException;
    void addLoan(Loan loanInfo) throws ClassNotFoundException, IOException;

}
