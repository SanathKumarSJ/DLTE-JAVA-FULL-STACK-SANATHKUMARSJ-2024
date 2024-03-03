package org.filesloan;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
public class MainClass implements MyBank {

    ArrayList<Loan> loans=new ArrayList<>();
    static File file=new File("C:\\DLTE-JAVA-FULL-STACK-SANATHKUMARSJ-2024\\DLTE-JAVA\\DLTE_JAVA-008\\TASK_FILES-610\\src\\main\\loan.txt");
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if(file.isFile())
            System.out.println("File created");
        else{
            System.out.println("Not created");
        }
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
                    Loan loanInfo = new Loan();
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

//    public Loan readInput() {
//        Scanner scanner = new Scanner(System.in);
//        Loan loanInfo = new Loan();
//        System.out.println("Thanks for choosing MyBank\nFor creating new account please provide the following details");
//        System.out.println("Enter the loan amount");
//        loanInfo.setLoanAmount(scanner.nextDouble());
//        System.out.println("Enter the contact number");
//        loanInfo.setBorrowerContact(scanner.nextLong());
//        System.out.println("Enter the loan number");
//        loanInfo.setLoanNumber(scanner.nextLong());
//        System.out.println("Enter borrower name");
//        loanInfo.setBorrowerName(scanner.next());
//        System.out.println("Enter the date");
//        loanInfo.setLoanDate(Date.parse(scanner.next()));
//        System.out.println("Status");
//        loanInfo.setLoanStatus(scanner.next());
//        return loanInfo;

//    }
    @Override
    public void addLoan(Loan loanInfo) throws IOException, ClassNotFoundException {
        //readLoanFromFile();
        loans.add(loanInfo);
        writeLoanToFile();
        System.out.println("Loan Added Successfully");
        }
    @Override
    public void checkAvailableLoan() throws IOException, ClassNotFoundException {
        readLoanFromFile();
        System.out.println("Available Loans");
        for(Loan each:loans){
            if(each.getLoanStatus().equals("open")){
                System.out.println(each);
            }
        }
    }

    @Override
    public void getCheckClosedLoan() throws IOException, ClassNotFoundException {
        readLoanFromFile();
        System.out.println("Closed Loans");
        for(Loan each:loans){
            if(each.getLoanStatus().equals("close")){
                System.out.println(each);
            }

        }
    }

    private void readLoanFromFile() throws IOException, ClassNotFoundException {
        FileInputStream inputFile=new FileInputStream(file);
        ObjectInputStream inputLoan = new ObjectInputStream(inputFile);
        loans.addAll((ArrayList<Loan>) inputLoan.readObject());
        inputLoan.close();
        inputFile.close();

    }
    private void writeLoanToFile() throws IOException, ClassNotFoundException {
        FileOutputStream outputFile=new FileOutputStream(file);
        ObjectOutputStream outputLoan = new ObjectOutputStream(outputFile);
        outputLoan.writeObject(loans);
        outputLoan.close();
        outputFile.close();

    }
}

