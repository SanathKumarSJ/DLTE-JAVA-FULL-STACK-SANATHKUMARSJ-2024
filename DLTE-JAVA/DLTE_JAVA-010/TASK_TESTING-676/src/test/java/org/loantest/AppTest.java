package org.loantest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;
/**
 * Unit test for simple App.
 */
public class AppTest
{
    static ArrayList<Loan> loans=new ArrayList<>();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testAddLoan() throws IOException, ClassNotFoundException {
        MainClass mainClass=new MainClass();

        Loan loan=new Loan(78484848L,78848.00,"12/12/2024","open","sanath",987984984L);
        mainClass.addLoan(loan);
        assertEquals("sanath",mainClass.loanInfo.get(0).getBorrowerName());
    }
    @Test
    public void testAvailability() throws IOException, ClassNotFoundException {
        MainClass mainClass=new MainClass();
        Loan loan=new Loan(78484848L,17848.00,"12/12/2024","open","sanath",987984984L);
        mainClass.addLoan(loan);
        mainClass.checkAvailableLoan();
        assertEquals("open",mainClass.loanInfo.get(0).getLoanStatus());
    }

    @Test
    public void testClosedLoan() throws IOException, ClassNotFoundException {
        MainClass mainClass=new MainClass();
        Loan loan=new Loan(78484848L,20848.00,"1/12/2024","open","sanath",987984984L);
        Loan loan1=new Loan(75884848L,17848.00,"12/1/2024","close","rakesh",97974984L);
        mainClass.addLoan(loan);
        mainClass.addLoan(loan1);
        mainClass.checkAvailableLoan();
        mainClass.getCheckClosedLoan();
        assertNotEquals("close",mainClass.loanInfo.get(0).getLoanStatus());
        assertEquals("close",mainClass.loanInfo.get(1).getLoanStatus());
    }
}