package org.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {


    public static void main(String[] args) {
        App app = new App();
//        final List<Loan> loanList = new ArrayList<>();
        Loan[] loans = {
                new Loan(85781523423L, 150000.0, new Date(2024, 8, 2), "Open", "sanath", 987655456L),
                new Loan(855555221L, 1850000.0, new Date(2023, 7, 14), "close", "akash", 8527419639L),
                new Loan(3744339227L, 690000.0, new Date(2025, 6, 25), "open", "mahesh", 9895585243L),
                new Loan(7252525225L, 785500.0, new Date(2025, 5, 26), "close", "rohith", 8585853726L),};
        ArrayList<Loan> loanList = new ArrayList<>();
        for (Loan loan : loans) {
            loanList.add(loan);
        }

        Date start=new Date(2025, 01, 10);
        Date end=new Date(2026, 02, 10);
        //filtering based on the date
        List<Loan> filteredLoans = loanList.stream().filter(loan -> loan.getLoanDate().after(start)&& loan.getLoanDate().before(end)).collect(Collectors.toList());
        System.out.println("The filtered loan displayed below");
        for (Loan each : filteredLoans) {
            System.out.println(each);
        }

    }
}