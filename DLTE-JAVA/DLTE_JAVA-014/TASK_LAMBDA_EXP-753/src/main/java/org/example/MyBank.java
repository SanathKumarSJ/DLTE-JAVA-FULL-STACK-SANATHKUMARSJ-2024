package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MyBank {
    ArrayList<Loan> loanList = new ArrayList<>();

    static List<Loan> filterByDate(Date start, Date end) {
        return null;
    }
}
