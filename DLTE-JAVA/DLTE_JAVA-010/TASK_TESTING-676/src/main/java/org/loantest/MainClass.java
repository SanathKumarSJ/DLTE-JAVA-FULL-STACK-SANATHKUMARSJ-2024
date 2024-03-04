package org.loantest;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
public class MainClass implements MyBank {
    ArrayList<Loan> loanInfo=new ArrayList<>();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MainClass mainClass = new MainClass();


        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a new loan");
            System.out.println("2. List available loans");
            System.out.println("3. List closed loans");
            System.out.println("4. Exit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    //Adding new loan
                    //Loan loan = new Loan();
                    System.out.println("Thanks for choosing MyBank\nFor creating new account please provide the following details");
                    System.out.println("Enter the loan amount");
                    Double amount=scanner.nextDouble();
                    System.out.println("Enter the contact number");
                    Long contact=scanner.nextLong();
                    System.out.println("Enter the loan number");
                    Long loanNumber=scanner.nextLong();
                    System.out.println("Enter borrower name");
                    String borrowerName=scanner.next();
                    System.out.println("Enter the date");
                    String date = scanner.next();
                    System.out.println("Status open/close");
                    String status= scanner.next();
                    mainClass.addLoan(new Loan(loanNumber,amount,date,status,borrowerName,contact));
                    break;
                case 2:
                    // Display available loans
                    mainClass.checkAvailableLoan();
                    break;
                case 3:
                    // Checking closed loan
                    mainClass.getCheckClosedLoan();
                    break;
                case 4:
                    System.out.println("Exiting. Have a great day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    @Override
    public void addLoan(Loan loan) throws IOException, ClassNotFoundException {
        //readLoanFromFile();
        loanInfo.add(loan);
        System.out.println("Loan Added Successfully");
        }
    @Override
    public void checkAvailableLoan() throws IOException, ClassNotFoundException {
        System.out.println("Available Loans");
        for(Loan each:loanInfo){
            if(each.getLoanStatus().equals("open")){
                System.out.println(each);
            }
        }
    }

    @Override
    public void getCheckClosedLoan() throws IOException, ClassNotFoundException {
        System.out.println("Closed Loans");
        for(Loan each:loanInfo){
            if(each.getLoanStatus().equals("close")){
                System.out.println(each);
            }

        }
    }
}

